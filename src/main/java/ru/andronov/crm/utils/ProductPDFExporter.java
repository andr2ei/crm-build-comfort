package ru.andronov.crm.utils;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import ru.andronov.crm.domain.Product;


public class ProductPDFExporter {
    private final List<Product> products;
    private final int discount;

    public ProductPDFExporter(List<Product> products, int discount) {
        this.products = products;
        this.discount = discount;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.LIGHT_GRAY);
        cell.setPadding(2);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.GREEN);

        cell.setPhrase(new Phrase("Товар"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Комментарий"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Кол-во"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Цена"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Стоимосить"));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (Product product : products) {
            table.addCell(product.getName());
            table.addCell(product.getComment());
            table.addCell(String.valueOf(product.getCount()));
            table.addCell(String.valueOf(product.getPrice()));
            table.addCell(String.valueOf(product.getCount() * product.getPrice()));
        }
    }

    private double countTotalCost() {
        var total = products.stream()
                .map(p -> p.getPrice() * p.getCount())
                .reduce(0.0, Double::sum);
        return Math.round(total * 100) / 100.0;
    }

    private double countTotalCostWithDiscount() {
        return Math.round(countTotalCost() * (1 - discount / 100.0) * 100) / 100.0;
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        try (Document document = new Document(PageSize.A4)) {
            PdfWriter.getInstance(document, response.getOutputStream());

            document.open();

            Image jpg = Image.getInstance("src/main/resources/cat_on_the_window.jpg");
            jpg.scaleAbsolute(65f, 80f);
            document.add(jpg);

            var firstPar = new Paragraph("Коммерческое предложение");
            firstPar.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(firstPar);

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100f);
            table.setWidths(new float[]{2f, 3.5f, 1.5f, 3.0f, 3.0f});
            table.setSpacingBefore(10);
            writeTableHeader(table);
            writeTableData(table);
            document.add(table);

            var totalCost = countTotalCost();
            var totalCostWithDiscount = countTotalCostWithDiscount();
            var lastPar = new Paragraph("Итого " + totalCost + ". Итого со скидкой " + discount + "% " + totalCostWithDiscount);
            lastPar.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(lastPar);
        }
    }
}
