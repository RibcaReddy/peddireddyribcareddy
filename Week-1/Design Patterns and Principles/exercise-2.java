interface Document {
    void open();
    void close();
    void save();
}
class WordDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening Word document...");
    }
    @Override
    public void close() {
        System.out.println("Closing Word document...");
    }
    @Override
    public void save() {
        System.out.println("Saving Word document...");
    }
}
class PdfDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening PDF document...");
    }
    @Override
    public void close() {
        System.out.println("Closing PDF document...");
    }
    @Override
    public void save() {
        System.out.println("Saving PDF document...");
    }
}
class ExcelDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening Excel document...");
    }
    @Override
    public void close() {
        System.out.println("Closing Excel document...");
    }
    @Override
    public void save() {
        System.out.println("Saving Excel document...");
    }
}
abstract class DocumentFactory {
    public abstract Document createDocument();
    public void openDocument() {
        Document doc = createDocument();
        doc.open();
    }
    public void closeDocument() {
        Document doc = createDocument();
        doc.close();
    }
    public void saveDocument() {
        Document doc = createDocument();
        doc.save();
    }
}
class WordDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new WordDocument();
    }
}
class PdfDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new PdfDocument();
    }
}
class ExcelDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new ExcelDocument();
    }
}
public class FactoryMethodPatternExample {
    public static void main(String[] args) {
        DocumentFactory wordFactory = new WordDocumentFactory();
        DocumentFactory pdfFactory = new PdfDocumentFactory();
        DocumentFactory excelFactory = new ExcelDocumentFactory();
        System.out.println("Testing WordDocumentFactory:");
        Document wordDoc = wordFactory.createDocument();
        wordDoc.open();
        wordDoc.save();
        wordDoc.close();
        System.out.println("\nTesting PdfDocumentFactory:");
        Document pdfDoc = pdfFactory.createDocument();
        pdfDoc.open();
        pdfDoc.save();
        pdfDoc.close();
        System.out.println("\nTesting ExcelDocumentFactory:");
        Document excelDoc = excelFactory.createDocument();
        excelDoc.open();
        excelDoc.save();
        excelDoc.close();
    }
}
