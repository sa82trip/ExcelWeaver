package com.retheviper.excel.test;


import com.retheviper.excel.definition.BookDef;
import com.retheviper.excel.test.header.Contract;
import com.retheviper.excel.test.testbase.CreateData;
import com.retheviper.excel.worker.BookWorker;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.IntStream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExcelTest {

    private static final String TEMPLATE_PATH = "src/test/resources/template/tf03783921_win321.xlsx";

    private static final String OUTPUT_PATH = "src/test/resources/temp/output.xlsx";

    private static final BookDef bookDef = new BookDef();

    private static final List<Contract> data = CreateData.getCreatedData();

    @BeforeAll
    public static void setup() {
        Assertions.assertDoesNotThrow(() -> bookDef.addSheet(Contract.class));
    }

    @Test
    @Order(1)
    public void writeTest() {
        Assertions.assertDoesNotThrow(() -> {
            try (BookWorker worker = bookDef.openBook(TEMPLATE_PATH, OUTPUT_PATH)) {
                worker.write(data);
            }
        });
    }

    @Test
    @Order(2)
    public void readTest() {
        Assertions.assertDoesNotThrow(() -> {
            List<Contract> list;
            try (BookWorker worker = bookDef.openBook(OUTPUT_PATH)) {
                list = worker.read(Contract.class);
            }
            IntStream.range(0, data.size()).forEach(index -> {
                final Contract expected = data.get(index);
                final Contract actual = list.get(index);
                Assertions.assertEquals(expected.getName(), actual.getName());
                Assertions.assertEquals(expected.getCompanyPhone(), actual.getCompanyPhone());
                Assertions.assertEquals(expected.getCellPhone(), actual.getCellPhone());
                Assertions.assertEquals(expected.getHomePhone(), actual.getHomePhone());
                Assertions.assertEquals(expected.getEmail(), actual.getEmail());
                Assertions.assertEquals(expected.getBirth().toString(), actual.getBirth().toString());
                Assertions.assertEquals(expected.getAddress(), actual.getAddress());
                Assertions.assertEquals(expected.getCity(), actual.getCity());
                Assertions.assertEquals(expected.getProvince(), actual.getProvince());
                Assertions.assertEquals(expected.getPost(), actual.getPost());
                Assertions.assertEquals(expected.getMemo(), actual.getMemo());
            });
        });
    }


    @AfterAll
    public static void deleteFile() throws IOException {
        Files.delete(Path.of(OUTPUT_PATH));
    }
}
