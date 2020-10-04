package com.example.myreader;

import java.util.ArrayList;
import java.util.List;

public class BookParser {
    public List<String> bookParse(String book) {
        List<String> quotes = new ArrayList<>();
        int nextQuote = 1, currIndex, nextIndex = 0;
        int lastIndex = book.length() - 1;
        try {
            do {
                currIndex = book.indexOf(nextQuote + ".", nextIndex);
                nextQuote++;
                nextIndex = book.indexOf(nextQuote + ".", currIndex);
                if (nextIndex == -1) {
                    nextIndex = lastIndex;
                }
                String curQuote = book.substring(currIndex, nextIndex);
                quotes.add(curQuote.substring(curQuote.indexOf(".") + 1, curQuote.length() - 1));
            } while (nextIndex != lastIndex);
        } catch (Exception e){
            e.printStackTrace();
        }
        return quotes;
    }
}
