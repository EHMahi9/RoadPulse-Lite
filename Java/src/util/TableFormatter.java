package util;

import java.util.List;

/**
 * Utility for formatting data into aligned table columns.
 */
public class TableFormatter {

    private TableFormatter() {
    }

    /**
     * Print a table with a header row and data rows.
     * Each column is auto-sized to the longest entry.
     */
    public static void printTable(String title, String[] headers, List<String[]> rows) {

        int[] colWidths = new int[headers.length];

        for (int i = 0; i < headers.length; i++) {
            colWidths[i] = headers[i].length();
        }

        for (String[] row : rows) {

            for (int i = 0; i < Math.min(row.length, headers.length); i++) {

                if (row[i] != null && row[i].length() > colWidths[i]) {
                    colWidths[i] = row[i].length();
                }

            }

        }

        // Build separator line
        StringBuilder sep = new StringBuilder("+");
        for (int w : colWidths) {
            sep.append("-".repeat(w + 2)).append("+");
        }

        // Title
        System.out.println();
        System.out.println(title);
        System.out.println(sep);

        // Header
        StringBuilder headerLine = new StringBuilder("|");
        for (int i = 0; i < headers.length; i++) {
            headerLine.append(" ").append(padRight(headers[i], colWidths[i])).append(" |");
        }
        System.out.println(headerLine);
        System.out.println(sep);

        // Rows
        for (String[] row : rows) {

            StringBuilder line = new StringBuilder("|");

            for (int i = 0; i < headers.length; i++) {

                String cell = (i < row.length && row[i] != null) ? row[i] : "";
                line.append(" ").append(padRight(cell, colWidths[i])).append(" |");

            }

            System.out.println(line);

        }

        System.out.println(sep);

    }

    private static String padRight(String s, int width) {

        if (s.length() >= width) {
            return s;
        }

        return s + " ".repeat(width - s.length());

    }

}
