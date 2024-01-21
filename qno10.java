import java.sql.*;
import java.util.Scanner;

public class qno10 {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/student_details";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();

            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("\nChoose an operation:");
                System.out.println("1. Add Record");
                System.out.println("2. Retrieve Records");
                System.out.println("3. Update Record");
                System.out.println("4. Delete Record");
                System.out.println("5. Exit");

                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Enter ID:");
                        int idToAdd = sc.nextInt();
                        System.out.println("Enter Name:");
                        String nameToAdd = sc.next();
                        addRecord(statement, idToAdd, nameToAdd);
                        break;

                    case 2:
                        retrieveRecords(statement);
                        break;

                    case 3:
                        System.out.println("Enter ID to update:");
                        int idToUpdate = sc.nextInt();
                        System.out.println("Enter New Name:");
                        String newName = sc.next();
                        updateRecord(statement, idToUpdate, newName);
                        break;

                    case 4:
                        System.out.println("Enter ID to delete:");
                        int idToDelete = sc.nextInt();
                        deleteRecord(statement, idToDelete);
                        break;

                    case 5:
                        // Close resources and exit
                        statement.close();
                        connection.close();
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addRecord(Statement statement, int id, String name) throws SQLException {
        String addQuery = "INSERT INTO student (id, name) VALUES (" + id + ", '" + name + "')";
        statement.executeUpdate(addQuery);
        System.out.println("Record added successfully.");
    }

   
    private static void retrieveRecords(Statement statement) throws SQLException {
    ResultSet resultSet = statement.executeQuery("SELECT * FROM student");
    
    System.out.println("ID\tName");
    while (resultSet.next()) 
        System.out.println(resultSet.getInt("id") + "\t" + resultSet.getString("name"));

    resultSet.close();
}


    private static void updateRecord(Statement statement, int id, String newName) throws SQLException {
        String updateQuery = "UPDATE student SET name='" + newName + "' WHERE id=" + id;
        statement.executeUpdate(updateQuery);
        System.out.println("Record updated successfully.");
    }

    private static void deleteRecord(Statement statement, int id) throws SQLException {
        String deleteQuery = "DELETE FROM student WHERE id=" + id;
        statement.executeUpdate(deleteQuery);
        System.out.println("Record deleted successfully.");
    }
}