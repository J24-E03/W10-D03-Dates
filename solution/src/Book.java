public class Book {

    private String title;
    private int numberOfPages;
    private String author;
    private boolean isBestSeller;
    private boolean isAvaialble;

    public Book(String title, int numberOfPages, String author, boolean isBestSeller, boolean isAvaialble) {
        this.title = title;
        this.numberOfPages = numberOfPages;
        setAuthor(author);
        this.isBestSeller = isBestSeller;
        this.isAvaialble = isAvaialble;
    }

    public Book() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if(author.isEmpty()){
            System.out.println("Please give a proper name");
            return;
        }
        this.author = author;
    }

    public boolean isBestSeller() {
        return isBestSeller;
    }

    public void setBestSeller(boolean bestSeller) {
        isBestSeller = bestSeller;
    }

    public boolean isAvaialble() {
        return isAvaialble;
    }

    public void setAvaialble(boolean avaialble) {
        isAvaialble = avaialble;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", author='" + author + '\'' +
                ", isBestSeller=" + isBestSeller +
                ", isAvaialble=" + isAvaialble +
                '}';
    }
}
