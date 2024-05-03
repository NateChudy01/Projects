import tkinter as tk
from tkinter import messagebox, ttk, scrolledtext
import mysql.connector
from mysql.connector import Error


class App(tk.Tk):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
        self.title("Red Foxes Databases")
        self.geometry("1024x768")
        self.frames = {}
        self.db_connection = None
        self.create_frames()
        self.protocol("WM_DELETE_WINDOW", self.on_close)
        self.available_tables = ['Account', 'Bank', 'Bank_Employee_Record', 'Customer', 'Employee', 'Landlord', 'Lease', 'Lease', 'Loan', 'Properties', 'RealEstateCompany', 'RealEstate_To_Address', 'SSN', 'Unit', 'CustomerInfo', 'EmployeeInfo']

    def set_db_connection(self, connection):
        self.db_connection = connection

    def get_db_connection(self):
        return self.db_connection

    def on_close(self):
        if self.db_connection is not None:
            self.db_connection.close()
        self.destroy()

    def create_frames(self):
        self.grid_rowconfigure(0, weight=1)
        self.grid_columnconfigure(0, weight=1)
        for F in (LoginPage, MainMenu, selectTable, InsertDataPage, DeleteDataPage, SearchPage, UpdateDataPage):
            frame = F(self)
            self.frames[F] = frame
            frame.grid(row=0, column=0, sticky="nsew")
        self.show_frame(LoginPage)

    def show_frame(self, context):
        frame = self.frames[context]
        if hasattr(frame, "load_data"):
            frame.load_data(self.available_tables[0])
        frame.tkraise()


class LoginPage(tk.Frame):
    def __init__(self, parent):
        super().__init__(parent)
        self.configure(bg='white')
        self.parent = parent
        # Configure the frame to expand in the middle
        self.grid_rowconfigure(0, weight=1)
        self.grid_columnconfigure(0, weight=1)
        self.setup_ui()

    def setup_ui(self):
        # Create a frame for login components with some padding for aesthetics
        login_frame = tk.Frame(self, bg='white', relief=tk.FLAT, bd=2)
        # Place the login_frame in the center of the grid
        login_frame.grid(row=0, column=0, sticky="nsew", padx=385, pady=150)

        # Label for the login page
        label_welcome = tk.Label(login_frame, text="Red Foxes Databases", bg='white', fg="#ff6961", font=('Georgia', 22, 'bold'))
        label_welcome.grid(row=0, column=0, pady=(20, 10), sticky="ew")

        # Username field
        label_username = tk.Label(login_frame, text="Username", bg='white', fg="#ff6961", font='Georgia')
        label_username.grid(row=1, column=0, sticky="ew")
        self.entry_username = tk.Entry(login_frame, bd=1, highlightthickness=1, highlightbackground="#ff6961")
        self.entry_username.grid(row=2, column=0, pady=(5, 20), sticky="ew")

        # Password field
        label_password = tk.Label(login_frame, text="Password", bg='white', fg="#ff6961", font='Georgia')
        label_password.grid(row=3, column=0, sticky="ew")
        self.entry_password = tk.Entry(login_frame, show="*", bd=1, highlightthickness=1, highlightbackground="#ff6961")
        self.entry_password.grid(row=4, column=0, pady=(5, 20), sticky="ew")

        # Login button
        button_login = tk.Button(login_frame, text="Login", command=self.attempt_login, fg="#ff6961", bg='white', bd=1, relief='flat', font=('Georgia', 10, 'bold'))
        button_login.grid(row=5, column=0, pady=(20, 20), sticky="ew")

    def attempt_login(self):
        username = self.entry_username.get()
        password = self.entry_password.get()

        try:
            # Attempt to connect to the MySQL database
            connection = mysql.connector.connect(
                host='localhost',
                user=username,
                passwd=password,
                database='red_foxes_database'
            )
            if connection.is_connected():
                messagebox.showinfo("Login Success", "Successfully connected to the database.")
                self.parent.set_db_connection(connection)
                self.parent.show_frame(MainMenu)  # Navigate to MainMenu after successful login
        except Error as e:
            messagebox.showerror("Login Failed", str(e))
        finally:
            if 'connection' in locals() and connection.is_connected():
                pass


class MainMenu(tk.Frame):
    def __init__(self, parent):
        super().__init__(parent)
        self.configure(bg='white')
        self.parent = parent
        # Configure the frame to expand in the middle
        self.grid_rowconfigure(0, weight=1)
        self.grid_rowconfigure(2, weight=1)  # Extra weight below to maintain vertical centering
        self.grid_columnconfigure(0, weight=1)
        self.setup_ui()

    def setup_ui(self):
        # Centering Frame
        center_frame = tk.Frame(self, bg='white')
        # Center the frame within its grid cell
        center_frame.grid(row=1, column=0, sticky="nsew")
        center_frame.grid_rowconfigure(0, weight=1)
        center_frame.grid_rowconfigure(4, weight=1)  # Extra row for spacing
        center_frame.grid_columnconfigure(0, weight=1)
        center_frame.grid_columnconfigure(1, weight=1)
        center_frame.grid_columnconfigure(2, weight=1)

        # Main Menu title
        label_title = tk.Label(center_frame, text="Main Menu", bg='white', fg="#ff6961", font=('Georgia', 22, 'bold'))
        label_title.grid(row=1, column=1, pady=(20, 20))  # Center column

        # VIEW TABLE Button
        button_employees = tk.Button(center_frame, text="View Tables", command=self.goto_selectTable, bg="white", fg="#ff6961", font=('Georgia', 12, 'bold'))
        button_employees.grid(row=2, column=1, pady=10)

        # INSERT INTO Button
        insert_button = tk.Button(center_frame, text="Insert Data", command=self.goto_insertData, bg="white", fg="#ff6961", font=('Georgia', 12, 'bold'))
        insert_button.grid(row=3, column=1, pady=10)

        # DELETE Button
        insert_button = tk.Button(center_frame, text="Delete Data", command=self.goto_deleteData, bg="white", fg="#ff6961", font=('Georgia', 12, 'bold'))
        insert_button.grid(row=4, column=1, pady=10)

        # CUSTOM QUERY Button
        insert_button = tk.Button(center_frame, text="Query Data", command=self.goto_searchPage, bg="white", fg="#ff6961", font=('Georgia', 12, 'bold'))
        insert_button.grid(row=5, column=1, pady=10)

        # UPDATE DATA Button
        insert_button = tk.Button(center_frame, text="Update Data", command=self.goto_searchPage, bg="white", fg="#ff6961", font=('Georgia', 12, 'bold'))
        insert_button.grid(row=6, column=1, pady=10)

    def goto_selectTable(self):
        self.parent.show_frame(selectTable)  # Navigate to view tables

    def goto_insertData(self):
        self.parent.show_frame(InsertDataPage)  # Navigate to Insert Data page

    def goto_deleteData(self):
        self.parent.show_frame(DeleteDataPage)  # Navigate to Insert Data page

    def goto_searchPage(self):
        self.parent.show_frame(SearchPage) # Navigate to Custom Query Page

    def goto_updatePage(self):
        self.parent.show_frame(UpdateDataPage) # Navigate to Update Data Page


class selectTable(tk.Frame):
    def __init__(self, parent):
        super().__init__(parent)
        self.configure(bg='white')
        self.parent = parent
        self.available_tables = ['Account', 'Account_SSN', 'Bank', 'Bank_Employee_Record', 'Customer', 'Customer_BankCode', 'Customer_SSN', 'Employee', 'Employee_SSN', 'Landlord', 'Landlord_Address', 'Landlord_SSN', 'Lease', 'Lease_SSN', 'Loan', 'Loan_SSN', 'Occupation', 'Properties', 'RealEstateCompany', 'RealEstate_To_Address', 'SSN', 'Unit', 'Unit_LeaseID', 'CustomerInfo', 'EmployeeInfo']
        self.setup_ui()

    def setup_ui(self):
        # Centering Frame to contain all widgets
        center_frame = tk.Frame(self, bg='white')
        center_frame.grid(row=0, column=0, sticky="nsew", padx=300, pady=50)
        self.grid_rowconfigure(0, weight=1)
        self.grid_columnconfigure(0, weight=1)

        # Combobox to select the table
        self.table_select = ttk.Combobox(center_frame, values=self.available_tables, state='readonly', width=30)
        self.table_select.grid(row=0, column=0, pady=(10, 10), padx=(20, 20))
        self.table_select.current(0)  # Set the default selection
        self.table_select.bind("<<ComboboxSelected>>", self.update_treeview)  # Bind selection change

        # Treeview Frame
        tree_frame = tk.Frame(center_frame, relief=tk.FLAT, bd=1)
        tree_frame.grid(row=1, column=0, sticky="nsew", padx=20, pady=10)
        center_frame.grid_rowconfigure(1, weight=1)
        center_frame.grid_columnconfigure(0, weight=1)

        # Treeview Widget
        self.tree = ttk.Treeview(tree_frame, show="headings", height=10)
        self.tree.pack(side='left', fill='both', expand=True)

        # Scrollbars
        v_scroll = ttk.Scrollbar(tree_frame, orient="vertical", command=self.tree.yview)
        v_scroll.pack(side='right', fill='y')
        h_scroll = ttk.Scrollbar(center_frame, orient="horizontal", command=self.tree.xview)
        h_scroll.grid(row=2, column=0, sticky="ew")

        self.tree.configure(xscrollcommand=h_scroll.set, yscrollcommand=v_scroll.set)

        # Main Menu button
        button_mainmenu = tk.Button(center_frame, text="Main Menu", command=self.goto_main, bg="white", fg="#ff6961", font=('Georgia', 12, 'bold'))
        button_mainmenu.grid(row=3, column=0, pady=(10, 10), sticky="ew")

    def update_treeview(self, event):
        table_name = self.table_select.get()
        self.load_data(table_name)

    def load_data(self, table_name):
        # Clear existing data in the tree
        for i in self.tree.get_children():
            self.tree.delete(i)

        # Fetch and display data
        connection = self.parent.get_db_connection()
        try:
            if connection is not None:
                cursor = connection.cursor()
                cursor.execute(f"SELECT * FROM {table_name}")
                rows = cursor.fetchall()
                # Dynamically adjust the treeview columns
                self.tree['columns'] = [desc[0] for desc in cursor.description]
                self.tree.heading("#0", text='', anchor='w')
                for col in self.tree['columns']:
                    self.tree.heading(col, text=col)
                    self.tree.column(col, anchor='w')
                for row in rows:
                    self.tree.insert("", tk.END, values=row)
        except Error as e:
            messagebox.showerror("Database Error: ", str(e))

    def goto_main(self):
        self.parent.show_frame(MainMenu)


class InsertDataPage(tk.Frame):
    def __init__(self, parent):
        super().__init__(parent)
        self.configure(bg='white')
        self.parent = parent
        self.entries = {}  # Initialize the entries dictionary here
        self.available_tables = ['Account', 'Account_SSN', 'Bank', 'Bank_Employee_Record', 'Customer', 'Customer_BankCode', 'Customer_SSN', 'Employee', 'Employee_SSN', 'Landlord', 'Landlord_Address', 'Landlord_SSN', 'Lease', 'Lease_SSN', 'Loan', 'Loan_SSN', 'Occupation', 'Properties', 'RealEstateCompany', 'RealEstate_To_Address', 'SSN', 'Unit', 'Unit_LeaseID', 'CustomerInfo', 'EmployeeInfo']
        self.setup_ui()

    def setup_ui(self):
        # Ensure the frame itself expands to fill available space
        self.grid_rowconfigure(0, weight=1)
        self.grid_columnconfigure(0, weight=1)

        # Central frame to hold all content in a centered layout
        center_frame = tk.Frame(self, bg='white')
        center_frame.grid(row=0, column=0, sticky="nsew")

        # Configure the central frame to align its content in the center
        center_frame.grid_rowconfigure(0, weight=1)  # Push content to the middle vertically
        center_frame.grid_rowconfigure(4, weight=1)  # Extra row at the bottom to ensure centering
        center_frame.grid_columnconfigure(0, weight=1)  # Center horizontally

        # Narrow frame for alignment
        alignmentI_frame = tk.Frame(center_frame, bg="white")
        alignmentI_frame.grid(row=2, column=0)

        # Narrow frame for alignment
        alignmentII_frame = tk.Frame(center_frame, bg="white")
        alignmentII_frame.grid(row=3, column=0)

        # Title label
        label_title = tk.Label(center_frame, text="Insert New Record", bg='white', fg="#ff6961", font=('Georgia', 22, 'bold'))
        label_title.grid(row=1, column=0, pady=20)

        # Combobox for table selection
        self.table_name = ttk.Combobox(alignmentI_frame, values=self.available_tables, font=('Georgia', 12), state='editable')
        self.table_name.grid(row=2, column=0, sticky='ew', padx=10, pady=10)
        self.table_name.set('Select or type a table name')  # Placeholder text

        # Button to confirm table selection and load fields dynamically
        confirm_button = ttk.Button(alignmentI_frame, text="Load Fields", command=self.load_fields)
        confirm_button.grid(row=3, column=0, pady=10)

        # Frame for dynamically created fields
        self.dynamic_frame = tk.Frame(alignmentI_frame, bg='white')
        self.dynamic_frame.grid(row=4, column=0, sticky='nsew', padx=300)

        # Main Menu button
        button_mainmenu = tk.Button(alignmentII_frame, text="Main Menu", command=self.goto_main, bg="white", fg="#ff6961", font=('Georgia', 12, 'bold'))
        button_mainmenu.grid(row=5, column=0, pady=10, sticky='ew')

    def load_fields(self):
        # Clears the previous entries and loads new ones based on the table selection
        for widget in self.dynamic_frame.winfo_children():
            widget.destroy()
        table = self.table_name.get()
        if table:
            connection = self.parent.get_db_connection()
            if connection is not None:
                cursor = connection.cursor()
                cursor.execute(f"DESCRIBE {table}")
                self.labels = [column[0] for column in cursor.fetchall()]
                cursor.close()
                row = 0
                for label in self.labels:
                    lbl = tk.Label(self.dynamic_frame, text=label, font=('Georgia', 12), bg='white', fg='black')
                    lbl.grid(row=row, column=0, sticky='e', padx=10, pady=10)
                    entry = ttk.Entry(self.dynamic_frame, font=('Georgia', 12))
                    entry.grid(row=row, column=1, sticky='ew', padx=10, pady=10)
                    self.entries[label] = entry
                    row += 1
                submit_button = ttk.Button(self.dynamic_frame, text="Insert Record", command=self.insert_record)
                submit_button.grid(row=row, column=0, columnspan=2, pady=20)

    def insert_record(self):
        # Function to insert the record into the database
        table = self.table_name.get()
        data = {label: self.entries[label].get() for label in self.labels}
        try:
            connection = self.parent.get_db_connection()
            if connection is not None:
                cursor = connection.cursor()
                fields = ', '.join(data.keys())
                values = ', '.join(['%s'] * len(data))
                query = f"INSERT INTO {table} ({fields}) VALUES ({values})"
                cursor.execute(query, tuple(data.values()))
                connection.commit()
                messagebox.showinfo("Success", "Record inserted successfully.")
                for entry in self.entries.values():
                    entry.delete(0, tk.END)
                cursor.close()
        except Error as e:
            messagebox.showerror("Error", str(e))

    def goto_main(self):
        # Function to navigate back to the main menu
        self.parent.show_frame(MainMenu)
        

class DeleteDataPage(tk.Frame):
    def __init__(self, parent):
        super().__init__(parent)
        self.configure(bg='white')
        self.parent = parent
        self.available_tables = ['Account', 'Account_SSN', 'Bank', 'Bank_Employee_Record', 'Customer', 'Customer_BankCode', 'Customer_SSN', 'Employee', 'Employee_SSN', 'Landlord', 'Landlord_Address', 'Landlord_SSN', 'Lease', 'Lease_SSN', 'Loan', 'Loan_SSN', 'Occupation', 'Properties', 'RealEstateCompany', 'RealEstate_To_Address', 'SSN', 'Unit', 'Unit_LeaseID', 'CustomerInfo', 'EmployeeInfo']
        self.setup_ui()

    def setup_ui(self):
        # Center frame to hold all widgets
        center_frame = tk.Frame(self, bg='white')
        center_frame.place(relx=0.5, rely=0.5, anchor='center')

        # Title Label
        label_title = tk.Label(center_frame, text="Delete Record", bg='white', fg="#ff6961", font=('Georgia', 22, 'bold'))
        label_title.grid(row=0, column=0, columnspan=2, pady=20)

        # Combobox to select the table
        self.table_name = ttk.Combobox(center_frame, values=self.available_tables, font=('Georgia', 12), state='readonly')
        self.table_name.grid(row=1, column=0, columnspan=2, sticky='ew', padx=10, pady=10)
        self.table_name.set('Select a table')  # Placeholder text

        # Entry for specifying the column name
        self.column_name = ttk.Entry(center_frame, font=('Georgia', 12))
        self.column_name.grid(row=2, column=0, columnspan=2, sticky='ew', padx=10, pady=10)
        self.column_name.insert(0, 'Enter column name')

        # Entry for specifying the value
        self.value = ttk.Entry(center_frame, font=('Georgia', 12))
        self.value.grid(row=3, column=0, columnspan=2, sticky='ew', padx=10, pady=10)
        self.value.insert(0, 'Enter value')

        # Delete button
        delete_button = ttk.Button(center_frame, text="Delete Record", command=self.delete_record)
        delete_button.grid(row=4, column=0, columnspan=2, pady=10)

        # Main Menu button
        button_mainmenu = tk.Button(center_frame, text="Main Menu", command=self.goto_main, bg="white", fg="#ff6961", font=('Georgia', 12, 'bold'))
        button_mainmenu.grid(row=5, column=0, columnspan=2, pady=10, sticky='ew')

    def delete_record(self):
        table = self.table_name.get()
        column = self.column_name.get()
        value = self.value.get()
        if table and column and value:
            try:
                connection = self.parent.get_db_connection()
                if connection is not None:
                    cursor = connection.cursor()
                    query = f"DELETE FROM {table} WHERE {column} = %s"
                    cursor.execute(query, (value,))
                    connection.commit()
                    if cursor.rowcount > 0:
                        messagebox.showinfo("Success", "Record deleted successfully.")
                    else:
                        messagebox.showinfo("Not Found", "Record not found.")
            except Error as e:
                messagebox.showerror("Error", str(e))
        else:
            messagebox.showerror("Input Error", "Please provide table name, column name, and value.")

    def goto_main(self):
        self.parent.show_frame(MainMenu)

class SearchPage(tk.Frame):
    def __init__(self, parent):
        super().__init__(parent, bg='white')
        self.parent = parent
        self.available_tables = ['Account', 'Account_SSN', 'Bank', 'Bank_Employee_Record', 'Customer', 'Customer_BankCode', 'Customer_SSN', 'Employee', 'Employee_SSN', 'Landlord', 'Landlord_Address', 'Landlord_SSN', 'Lease', 'Lease_SSN', 'Loan', 'Loan_SSN', 'Occupation', 'Properties', 'RealEstateCompany', 'RealEstate_To_Address', 'SSN', 'Unit', 'Unit_LeaseID', 'CustomerInfo', 'EmployeeInfo']
        self.setup_ui()

    def setup_ui(self):
        self.grid_rowconfigure(0, weight=1)
        self.grid_columnconfigure(0, weight=1)

        # Centering Frame
        center_frame = tk.Frame(self, bg='white')
        center_frame.place(relx=0.5, rely=0.5, anchor='center')

        # Table selection
        tk.Label(center_frame, text="Select Table:", bg='white').grid(row=0, column=0, sticky='ew', padx=10, pady=5)
        self.table_select = ttk.Combobox(center_frame, values=self.available_tables, state='readonly')
        self.table_select.grid(row=0, column=1, sticky='ew', padx=10, pady=5)
        self.table_select.bind("<<ComboboxSelected>>", self.update_columns)

        # Column selection
        tk.Label(center_frame, text="Select Columns:", bg='white').grid(row=1, column=0, sticky='ew', padx=10, pady=5)
        self.columns_select = tk.Listbox(center_frame, selectmode='multiple', exportselection=0)
        self.columns_select.grid(row=1, column=1, sticky='ew', padx=10, pady=5)

        # Conditions input
        tk.Label(center_frame, text="Conditions (WHERE):", bg='white').grid(row=2, column=0, sticky='ew', padx=10, pady=5)
        self.conditions_entry = tk.Entry(center_frame)
        self.conditions_entry.grid(row=2, column=1, sticky='ew', padx=10, pady=5)

        # Query result area
        self.result_text = scrolledtext.ScrolledText(center_frame, height=10)
        self.result_text.grid(row=3, column=0, columnspan=2, sticky='ew', padx=10, pady=5)

        # Buttons
        tk.Button(center_frame, text="Execute", command=self.execute_query).grid(row=4, column=0, padx=10, pady=5)
        tk.Button(center_frame, text="Clear", command=self.clear_results).grid(row=4, column=1, padx=10, pady=5)

        # Main Menu button
        button_mainmenu = tk.Button(center_frame, text="Main Menu", command=self.goto_main, bg="white", fg="#ff6961", font=('Georgia', 12, 'bold'))
        button_mainmenu.grid(row=5, column=1, columnspan=2, pady=10, sticky='ew')

    def update_columns(self, event):
        table = self.table_select.get()
        self.columns_select.delete(0, tk.END)
        if table:
            connection = self.parent.get_db_connection()
            if connection:
                cursor = connection.cursor()
                cursor.execute(f"DESCRIBE {table}")
                for row in cursor.fetchall():
                    self.columns_select.insert(tk.END, row[0])
                cursor.close()

    def execute_query(self):
        table = self.table_select.get()
        selected_indices = self.columns_select.curselection()
        selected_columns = ', '.join([self.columns_select.get(i) for i in selected_indices]) or '*'
        conditions = self.conditions_entry.get()
        query = f"SELECT {selected_columns} FROM {table} WHERE {conditions}" if conditions else f"SELECT {selected_columns} FROM {table}"
        try:
            connection = self.parent.get_db_connection()
            if connection:
                cursor = connection.cursor()
                cursor.execute(query)
                rows = cursor.fetchall()
                self.result_text.delete('1.0', tk.END)
                for row in rows:
                    self.result_text.insert(tk.END, f"{row}\n")
                cursor.close()
        except Exception as e:
            messagebox.showerror("Error", str(e))

    def clear_results(self):
        self.result_text.delete('1.0', tk.END)

    def goto_main(self):
        self.parent.show_frame(MainMenu)


class UpdateDataPage(tk.Frame):
    def __init__(self, parent):
        super().__init__(parent, bg='white')
        self.parent = parent
        self.available_tables = ['Account', 'Account_SSN', 'Bank', 'Bank_Employee_Record', 'Customer', 'Customer_BankCode', 'Customer_SSN', 'Employee', 'Employee_SSN', 'Landlord', 'Landlord_Address', 'Landlord_SSN', 'Lease', 'Lease_SSN', 'Loan', 'Loan_SSN', 'Occupation', 'Properties', 'RealEstateCompany', 'RealEstate_To_Address', 'SSN', 'Unit', 'Unit_LeaseID', 'CustomerInfo', 'EmployeeInfo']
        self.setup_ui()

    def setup_ui(self):
        self.grid_rowconfigure(0, weight=1)
        self.grid_columnconfigure(0, weight=1)

        # Centering Frame
        center_frame = tk.Frame(self, bg='white')
        center_frame.place(relx=0.5, rely=0.5, anchor='center')

        # Table selection
        tk.Label(center_frame, text="Select Table:", bg='white').grid(row=0, column=0, sticky='ew', padx=10, pady=5)
        self.table_select = ttk.Combobox(center_frame, values=self.available_tables, state='readonly')
        self.table_select.grid(row=0, column=1, sticky='ew', padx=10, pady=5)
        self.table_select.bind("<<ComboboxSelected>>", self.update_columns)

        # Column selection
        tk.Label(center_frame, text="Select Column to Update:", bg='white').grid(row=1, column=0, sticky='ew', padx=10, pady=5)
        self.column_select = ttk.Combobox(center_frame, state='readonly')
        self.column_select.grid(row=1, column=1, sticky='ew', padx=10, pady=5)

        # New value input
        tk.Label(center_frame, text="New Value:", bg='white').grid(row=2, column=0, sticky='ew', padx=10, pady=5)
        self.new_value_entry = tk.Entry(center_frame)
        self.new_value_entry.grid(row=2, column=1, sticky='ew', padx=10, pady=5)

        # Conditions input
        tk.Label(center_frame, text="Conditions (e.g., 'id = 4'):", bg='white').grid(row=3, column=0, sticky='ew', padx=10, pady=5)
        self.conditions_entry = tk.Entry(center_frame)
        self.conditions_entry.grid(row=3, column=1, sticky='ew', padx=10, pady=5)

        # Update button
        update_button = ttk.Button(center_frame, text="Update Record", command=self.update_record)
        update_button.grid(row=4, column=0, columnspan=2, pady=10)

        # Clear button
        clear_button = ttk.Button(center_frame, text="Clear", command=self.clear_form)
        clear_button.grid(row=5, column=0, columnspan=2, pady=10)

    def update_columns(self, event):
        table = self.table_select.get()
        self.column_select.set('')
        self.column_select['values'] = []
        if table:
            connection = self.parent.get_db_connection()
            if connection:
                cursor = connection.cursor()
                cursor.execute(f"DESCRIBE {table}")
                columns = [row[0] for row in cursor.fetchall()]
                self.column_select['values'] = columns
                cursor.close()

    def update_record(self):
        table = self.table_select.get()
        column = self.column_select.get()
        new_value = self.new_value_entry.get()
        conditions = self.conditions_entry.get()
        if table and column and new_value and conditions:
            try:
                connection = self.parent.get_db_connection()
                if connection:
                    cursor = connection.cursor()
                    query = f"UPDATE {table} SET {column} = %s WHERE {conditions}"
                    cursor.execute(query, (new_value,))
                    connection.commit()
                    messagebox.showinfo("Success", "Record updated successfully.")
                    cursor.close()
            except Exception as e:
                messagebox.showerror("Error", str(e))

    def clear_form(self):
        self.table_select.set('')
        self.column_select.set('')
        self.new_value_entry.delete(0, 'end')
        self.conditions_entry.delete(0, 'end')


if __name__ == "__main__":
    app = App()
    app.mainloop()
