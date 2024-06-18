import java.util.Scanner;

public class LibrarySystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Library System Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. Register Member");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. List Available Books");
            System.out.println("6. List Borrowed Books");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter book ISBN: ");
                    String ISBN = scanner.nextLine();
                    library.addBook(new Book(title, author, ISBN));
                    System.out.println("Book added successfully.");
                    break;
                case 2:
                    System.out.print("Enter member name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter member ID: ");
                    String memberId = scanner.nextLine();
                    library.registerMember(new Member(name, memberId));
                    System.out.println("Member registered successfully.");
                    break;
                case 3:
                    System.out.print("Enter book ISBN: ");
                    String borrowISBN = scanner.nextLine();
                    System.out.print("Enter member ID: ");
                    String borrowMemberId = scanner.nextLine();
                    if (library.borrowBook(borrowISBN, borrowMemberId)) {
                        System.out.println("Book borrowed successfully.");
                    } else {
                        System.out.println("Book borrowing failed.");
                    }
                    break;
                case 4:
                    System.out.print("Enter book ISBN: ");
                    String returnISBN = scanner.nextLine();
                    System.out.print("Enter member ID: ");
                    String returnMemberId = scanner.nextLine();
                    if (library.returnBook(returnISBN, returnMemberId)) {
                        System.out.println("Book returned successfully.");
                    } else {
                        System.out.println("Book return failed.");
                    }
                    break;
                case 5:
                    System.out.println("Available Books:");
                    library.listAvailableBooks().forEach(System.out::println);
                    break;
                case 6:
                    System.out.print("Enter member ID: ");
                    String listMemberId = scanner.nextLine();
                    System.out.println("Borrowed Books:");
                    library.listBorrowedBooks(listMemberId).forEach(System.out::println);
                    break;
                case 7:
                    System.out.println("Exiting system.");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}