
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Library {
    private List<Book> books;
    private List<Member> members;

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void registerMember(Member member) {
        members.add(member);
    }

    public boolean borrowBook(String ISBN, String memberId) {
        Optional<Book> bookOpt = books.stream().filter(b -> b.getISBN().equals(ISBN) && b.isAvailable()).findFirst();
        Optional<Member> memberOpt = members.stream().filter(m -> m.getMemberId().equals(memberId)).findFirst();

        if (bookOpt.isPresent() && memberOpt.isPresent()) {
            Book book = bookOpt.get();
            Member member = memberOpt.get();
            book.setAvailable(false);
            member.borrowBook(book);
            return true;
        }
        return false;
    }

    public boolean returnBook(String ISBN, String memberId) {
        Optional<Member> memberOpt = members.stream().filter(m -> m.getMemberId().equals(memberId)).findFirst();

        if (memberOpt.isPresent()) {
            Member member = memberOpt.get();
            Optional<Book> bookOpt = member.getBorrowedBooks().stream().filter(b -> b.getISBN().equals(ISBN)).findFirst();

            if (bookOpt.isPresent()) {
                Book book = bookOpt.get();
                book.setAvailable(true);
                member.returnBook(book);
                return true;
            }
        }
        return false;
    }

    public List<Book> listAvailableBooks() {
        return books.stream().filter(Book::isAvailable).collect(Collectors.toList());
    }

    public List<Book> listBorrowedBooks(String memberId) {
        return members.stream()
                .filter(m -> m.getMemberId().equals(memberId))
                .findFirst()
                .map(Member::getBorrowedBooks)
                .orElse(new ArrayList<>());
    }

    @Override
    public String toString() {
        return "Library{" +
                "books=" + books +
                ", members=" + members +
                '}';
    }
}
