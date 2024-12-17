import SwiftUI
import PhotosUI
import Foundation

struct PhotoPicker: View {
    @State private var selectedImages: [UIImage] = []
    @State private var isImagePickerPresented = false
    @State private var uploadStatus: String?
    @State private var uploadStatusColor: Color = .green
    @State private var showCompletionOverlay: Bool = false // New State for overlay
    
    var body: some View {
        ZStack {
            VStack {
                // Title and Description
                VStack(spacing: 8) {
                    Text("Photo Uploader")
                        .font(.largeTitle)
                        .fontWeight(.bold)
                        .foregroundColor(.blue)
                    
                    Text("Select photos and upload them to the cloud")
                        .font(.subheadline)
                        .foregroundColor(.gray)
                        .multilineTextAlignment(.center)
                }
                .padding(.vertical)
                
                // Image Display Section
                if selectedImages.isEmpty {
                    VStack {
                        Image(systemName: "photo.on.rectangle.angled")
                            .resizable()
                            .scaledToFit()
                            .frame(width: 100, height: 100)
                            .foregroundColor(.gray)
                        
                        Text("No photos selected")
                            .font(.headline)
                            .foregroundColor(.gray)
                    }
                    .padding()
                } else {
                    ScrollView(.horizontal, showsIndicators: false) {
                        LazyHGrid(rows: [GridItem(.fixed(100))], spacing: 10) {
                            ForEach(selectedImages, id: \.self) { image in
                                Image(uiImage: image)
                                    .resizable()
                                    .scaledToFill()
                                    .frame(width: 100, height: 100)
                                    .clipped()
                                    .cornerRadius(10)
                                    .shadow(radius: 3)
                            }
                        }
                        .padding()
                    }
                }
                
                // Button Section
                HStack(spacing: 16) {
                    // Select Photos Button
                    Button(action: {
                        isImagePickerPresented = true
                    }) {
                        Label("Select Photos", systemImage: "photo.on.rectangle")
                            .frame(maxWidth: .infinity)
                    }
                    .buttonStyle(FilledButtonStyle(color: .blue))
                    
                    // Cancel Selection Button
                    Button(action: {
                        selectedImages.removeAll()
                    }) {
                        Label("Clear", systemImage: "xmark.circle")
                            .frame(maxWidth: .infinity)
                    }
                    .buttonStyle(FilledButtonStyle(color: .red))
                }
                .padding(.top)
                
                Button(action: {
                    uploadImagesToLambda()
                }) {
                    Label("Upload Images", systemImage: "icloud.and.arrow.up")
                        .frame(maxWidth: .infinity)
                }
                .buttonStyle(FilledButtonStyle(color: .green))
                .disabled(selectedImages.isEmpty)
                .opacity(selectedImages.isEmpty ? 0.6 : 1.0)
                .padding(.vertical)
                
                // Status Banner
                if let uploadStatus = uploadStatus {
                    Text(uploadStatus)
                        .font(.headline)
                        .foregroundColor(.white)
                        .padding()
                        .frame(maxWidth: .infinity)
                        .background(uploadStatusColor)
                        .cornerRadius(10)
                        .padding(.horizontal)
                        .animation(.easeInOut, value: uploadStatus)
                }
            }
            .padding()
            .sheet(isPresented: $isImagePickerPresented) {
                ImagePicker(selectedImages: $selectedImages)
            }
            
            // Full-screen Completion Overlay
            if showCompletionOverlay {
                VStack {
                    Image(systemName: "checkmark.circle.fill")
                        .resizable()
                        .scaledToFit()
                        .frame(width: 150, height: 150)
                        .foregroundColor(.green)
                    
                    Text("Upload Complete!")
                        .font(.largeTitle)
                        .fontWeight(.bold)
                        .foregroundColor(.green)
                    
                    Text("\(selectedImages.count) image(s) successfully uploaded")
                        .font(.headline)
                        .foregroundColor(.gray)
                        .padding(.top, 8)
                }
                .padding()
                .background(Color.white)
                .cornerRadius(20)
                .shadow(radius: 10)
                .transition(.scale)
                .onTapGesture {
                    showCompletionOverlay = false // Dismiss on tap
                }
            }
        }
    }
    
    func uploadImagesToLambda() {
        guard !selectedImages.isEmpty else {
            uploadStatus = "No images selected for upload"
            return
        }
        
        uploadStatus = "Uploading images..."
        
        var completedUploads = 0
        var failedUploads = 0
        
        // Use the index to track the current image number
        for (index, image) in selectedImages.enumerated() {
            let currImage = index + 1 // Human-readable index (1-based)
            
            guard let orientedImage = fixImageOrientation(image) else {
                failedUploads += 1
                continue
            }
            
            guard let imageData = orientedImage.jpegData(compressionQuality: 0.8) else {
                print("Failed to convert image to JPEG")
                failedUploads += 1
                continue
            }
            
            let base64Image = imageData.base64EncodedString()
            
            let payload: [String: Any] = ["image": base64Image]
            guard let jsonData = try? JSONSerialization.data(withJSONObject: payload, options: []) else {
                print("Failed to create JSON payload")
                failedUploads += 1
                continue
            }
            
            guard let url = URL(string: "API-GATEWAY-NOT-INCLUDED") else {
                print("Invalid API URL")
                failedUploads += 1
                continue
            }
            
            var request = URLRequest(url: url)
            request.httpMethod = "POST"
            request.addValue("application/json; charset=utf-8", forHTTPHeaderField: "Content-Type")
            request.httpBody = jsonData
            
            let task = URLSession.shared.dataTask(with: request) { data, response, error in
                if let error = error {
                    print("Upload failed: \(error.localizedDescription)")
                    failedUploads += 1
                } else if let httpResponse = response as? HTTPURLResponse, httpResponse.statusCode != 200 {
                    print("Server responded with status code \(httpResponse.statusCode)")
                    failedUploads += 1
                } else {
                    // Update the upload status to show the current image uploaded only if there is more than one image
                    DispatchQueue.main.async {
                        if self.selectedImages.count > 1 {
                            self.uploadStatus = "Image \(currImage) uploaded successfully"
                        }
                    }
                }
                
                DispatchQueue.main.async {
                    completedUploads += 1
                    
                    if completedUploads == self.selectedImages.count {
                        self.uploadStatus = failedUploads == 0 ? "All images uploaded successfully!" : "\(failedUploads) image(s) failed to upload"
                        
                        // Show overlay if all uploads succeeded
                        if failedUploads == 0 {
                            self.showCompletionOverlay = true
                            // Dismiss overlay after a delay
                            DispatchQueue.main.asyncAfter(deadline: .now() + 2.0) {
                                self.showCompletionOverlay = false
                                
                                // Clear the selected images after the overlay is dismissed
                                self.selectedImages.removeAll()
                            }
                        }
                        
                        self.resetUploadStatusAfterDelay()
                    }
                }
            }
            task.resume()
        }
    }


    
    func resetUploadStatusAfterDelay() {
        DispatchQueue.main.asyncAfter(deadline: .now() + 2.0) {
            uploadStatus = nil
        }
    }
    
    func fixImageOrientation(_ image: UIImage) -> UIImage? {
        if image.imageOrientation == .up {
            return image
        }
        
        UIGraphicsBeginImageContextWithOptions(image.size, false, image.scale)
        image.draw(in: CGRect(origin: .zero, size: image.size))
        let fixedImage = UIGraphicsGetImageFromCurrentImageContext()
        UIGraphicsEndImageContext()
        
        return fixedImage
    }
}

struct FilledButtonStyle: ButtonStyle {
    var color: Color
    
    func makeBody(configuration: Configuration) -> some View {
        configuration.label
            .padding()
            .background(color.opacity(configuration.isPressed ? 0.8 : 1.0))
            .foregroundColor(.white)
            .cornerRadius(10)
            .shadow(radius: configuration.isPressed ? 0 : 5)
    }
}

struct ImagePicker: UIViewControllerRepresentable {
    @Binding var selectedImages: [UIImage] // Binding to hold selected images
    
    func makeCoordinator() -> Coordinator {
        Coordinator(self)
    }
    
    func makeUIViewController(context: Context) -> PHPickerViewController {
        var configuration = PHPickerConfiguration(photoLibrary: .shared())
        configuration.selectionLimit = 0 // 0 means no limit, allowing multiple selections
        configuration.filter = .images // Only allow images
        
        let picker = PHPickerViewController(configuration: configuration)
        picker.delegate = context.coordinator
        return picker
    }
    
    func updateUIViewController(_ uiViewController: PHPickerViewController, context: Context) {}
    
    class Coordinator: NSObject, PHPickerViewControllerDelegate {
        var parent: ImagePicker
        
        init(_ parent: ImagePicker) {
            self.parent = parent
        }
        
        func picker(_ picker: PHPickerViewController, didFinishPicking results: [PHPickerResult]) {
            picker.dismiss(animated: true)
            
            // Load the selected images
            for result in results {
                if result.itemProvider.canLoadObject(ofClass: UIImage.self) {
                    result.itemProvider.loadObject(ofClass: UIImage.self) { object, error in
                        if let image = object as? UIImage {
                            DispatchQueue.main.async {
                                self.parent.selectedImages.append(image) // Append each selected image
                            }
                        }
                    }
                }
            }
        }
    }
}


