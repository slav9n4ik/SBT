public class Main {
    public static void main(String[] args) {
        Journal journal = new Journal("News","John",48);
        JournalProcessor journalProcessor = new JournalProcessor("NoName", 0);
        BeanUtils.assign(journalProcessor, journal);

        System.out.println(journalProcessor.getTitle());
        System.out.println(journalProcessor.getPages());
    }
}
