package generator;

import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;

public class DocumentWorker {
    private static CTP createHeaderModel(String headerContent) {
        CTP ctpHeaderModel = CTP.Factory.newInstance();
        CTR ctrHeaderModel = ctpHeaderModel.addNewR();
        CTText cttHeader = ctrHeaderModel.addNewT();
        cttHeader.setStringValue(headerContent);
        return ctpHeaderModel;
    }

    public XWPFDocument generateReceipt(String contractNum, String date, String companyname) {
        System.out.println(contractNum+" "+date+" "+companyname);
        try {
            //Создаём документ
            XWPFDocument docxModel = new XWPFDocument();
            //Мне неведомая фигня, но она нужна
            CTSectPr ctSectPr = docxModel.getDocument().getBody().addNewSectPr();
            //Создаём объект для доступа к колонтитулами
            XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(docxModel, ctSectPr);
            //Создаем верхний колонтитул и задаем ему параметры
            CTP ctpHeaderModel = createHeaderModel("Акционерное общество \"Газпром межрегионгаз Нижний Новгород\"" +
                    " Адрес: Нижний Новгород 603005, Россия, г. Нижний Новгород, Верхне-Волжская набережная, дом 5.");
            XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeaderModel, docxModel);
            headerParagraph.setAlignment(ParagraphAlignment.CENTER);
            headerFooterPolicy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, new XWPFParagraph[]{headerParagraph});
            //Создаем заголовок в виде параграфа
            XWPFParagraph mainHeader = docxModel.createParagraph();
            //Задаем параметры для заголовка
            mainHeader.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun paragraphConfig = mainHeader.createRun();
            paragraphConfig.setFontSize(22);
            paragraphConfig.setBold(true);
            paragraphConfig.setColor("000000");
            paragraphConfig.setText("Счёт "+contractNum+" от "+date);

            XWPFParagraph paragraph1 = docxModel.createParagraph();

            paragraph1.setAlignment(ParagraphAlignment.CENTER);
            paragraphConfig = paragraph1.createRun();
            paragraphConfig.setFontSize(16);
            paragraphConfig.setBold(true);
            paragraphConfig.setColor("000000");
            paragraphConfig.setText("Образец заполнения платежного поручения");

            XWPFParagraph paragraph1_1 = docxModel.createParagraph();

            paragraph1.setAlignment(ParagraphAlignment.CENTER);
            paragraphConfig = paragraph1.createRun();
            paragraphConfig.setFontSize(16);

            //Создаем таблицу с образцом заполнения платежного поручения
            XWPFTable payExample = docxModel.createTable();
            //Задаем параметры для границ таблицы
            payExample.setTopBorder(XWPFTable.XWPFBorderType.SINGLE, 4, 2,"000000");
            payExample.setBottomBorder(XWPFTable.XWPFBorderType.SINGLE, 4, 2,"000000");
            payExample.setRightBorder(XWPFTable.XWPFBorderType.SINGLE, 4, 2,"000000");
            payExample.setLeftBorder(XWPFTable.XWPFBorderType.SINGLE, 4, 2,"000000");
            payExample.setInsideHBorder(XWPFTable.XWPFBorderType.SINGLE, 4, 2,"000000");
            payExample.setInsideVBorder(XWPFTable.XWPFBorderType.SINGLE, 4, 2,"000000");

            //Заполняем таблицу данными
            payExample.getRow(0).getCell(0).setText("Получатель" +
                    "межрегионгаз Нижний Новгород" +
                    "ИНН/КПП 5260070633/525350001");
            payExample.getRow(0).addNewTableCell().setText("р/сч №");
            payExample.getRow(0).addNewTableCell().setText("40702810700240000028");
            payExample.createRow();
            payExample.getRow(1).getCell(0).setText("Банк получателя" +
                    " г. НИЖНИЙ НОВГОРОД");
            payExample.getRow(1).getCell(1).setText("БИК" +
                    " кор.сч №");

            XWPFParagraph paragraph2_1 = docxModel.createParagraph();

            paragraph1.setAlignment(ParagraphAlignment.CENTER);
            paragraphConfig = paragraph1.createRun();
            paragraphConfig.setFontSize(22);

            XWPFParagraph paragraph2 = docxModel.createParagraph();

            paragraph2.setAlignment(ParagraphAlignment.CENTER);
            paragraphConfig = paragraph2.createRun();
            paragraphConfig.setFontSize(22);
            paragraphConfig.setBold(true);
            paragraphConfig.setColor("000000");
            paragraphConfig.setText("Наименование платежа: оплата по договору №100500 от "+date);

            XWPFParagraph paragraph2_2 = docxModel.createParagraph();

            paragraph1.setAlignment(ParagraphAlignment.CENTER);
            paragraphConfig = paragraph1.createRun();
            paragraphConfig.setFontSize(22);

            XWPFParagraph paragraph3 = docxModel.createParagraph();

            paragraph3.setAlignment(ParagraphAlignment.CENTER);
            paragraphConfig = paragraph3.createRun();
            paragraphConfig.setFontSize(16);
            paragraphConfig.setBold(true);
            paragraphConfig.setColor("000000");
            paragraphConfig.setText("Плательщик ООО фирма \""+companyname+"\"" +
                    "Основание: договор на поставку газа №100500");

            XWPFParagraph paragraph3_1 = docxModel.createParagraph();

            paragraph1.setAlignment(ParagraphAlignment.CENTER);
            paragraphConfig = paragraph1.createRun();
            paragraphConfig.setFontSize(16);

            XWPFTable receiptTable = docxModel.createTable();
            receiptTable.setTopBorder(XWPFTable.XWPFBorderType.SINGLE, 4, 2,"000000");
            receiptTable.setBottomBorder(XWPFTable.XWPFBorderType.SINGLE, 4, 2,"000000");
            receiptTable.setRightBorder(XWPFTable.XWPFBorderType.SINGLE, 4, 2,"000000");
            receiptTable.setLeftBorder(XWPFTable.XWPFBorderType.SINGLE, 4, 2,"000000");
            receiptTable.setInsideHBorder(XWPFTable.XWPFBorderType.SINGLE, 4, 2,"000000");
            receiptTable.setInsideVBorder(XWPFTable.XWPFBorderType.SINGLE, 4, 2,"000000");

            receiptTable.getRow(0).getCell(0).setText("");
            receiptTable.getRow(0).addNewTableCell().setText("Сумма (руб.)");
            receiptTable.createRow();
            receiptTable.getRow(1).getCell(0).setText("Оплата за поставку природного газа");
            receiptTable.getRow(1).getCell(1).setText("СУММА ЧТО АХРИНЕЛ руб.");
            receiptTable.createRow();
            receiptTable.getRow(2).getCell(0).setText("в том числе и НДС (20%)");
            receiptTable.getRow(2).getCell(1).setText("20% ОТ СУММЫ ЧТО АХРИНЕЛ руб.");
            receiptTable.createRow();
            receiptTable.getRow(3).getCell(0).setText("Оплатить в срок до 18 числа этого месяца");
            receiptTable.getRow(3).getCell(1).setText("СУММА ЧТО АХРИНЕЛ руб.");
            receiptTable.createRow();
            receiptTable.getRow(4).getCell(0).setText("в том числе и НДС (20%)");
            receiptTable.getRow(4).getCell(1).setText("20% ОТ СУММЫ ЧТО АХРИНЕЛ руб.");
            receiptTable.createRow();
            receiptTable.getRow(5).getCell(0).setText("Оплатить в срок до 30 числа этого месяца");
            receiptTable.getRow(5).getCell(1).setText("СУММА ЧТО АХРИНЕЛ руб.");
            receiptTable.createRow();
            receiptTable.getRow(6).getCell(0).setText("в том числе и НДС (20%)");
            receiptTable.getRow(6).getCell(1).setText("20% ОТ СУММЫ ЧТО АХРИНЕЛ руб.");
            receiptTable.createRow();
            receiptTable.getRow(7).getCell(0).setText("Итого:");
            receiptTable.getRow(7).getCell(1).setText("Очень много");

            XWPFParagraph paragraph4_1 = docxModel.createParagraph();

            paragraph1.setAlignment(ParagraphAlignment.CENTER);
            paragraphConfig = paragraph1.createRun();
            paragraphConfig.setFontSize(16);

            XWPFParagraph paragraph4 = docxModel.createParagraph();

            paragraph4.setAlignment(ParagraphAlignment.CENTER);
            paragraphConfig = paragraph4.createRun();
            paragraphConfig.setFontSize(16);
            paragraphConfig.setBold(true);
            paragraphConfig.setColor("000000");
            paragraphConfig.setText("В платежном поручении ссылка на номер договора обязательна!");
            //Возвращаем построенную модель документа
            return docxModel;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
