package com.example.myreader;


import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BookParserTest {
    private BookParser curtest = new BookParser();

    @Test
    public void test() {
        String testString = "1. jkbkjk 2. knjn 3. lkkjnk";
        List<String> ans = curtest.bookParse(testString);
        Assert.assertEquals(3, ans.size());
        Assert.assertEquals("1. jkbkjk ", ans.get(0));
    }

    @Test
    public void trickyNumeration() {
        String testString = "1. jkb3.kjk 2. knjn 3. lkkjnk";
        List<String> ans = curtest.bookParse(testString);
        Assert.assertEquals(3, ans.size());
        Assert.assertEquals("1. jkb3.kjk ", ans.get(0));
    }

    @Test
    public void test3() {
        String testString = "А.А. Шалыто\n" +
                "докт. техн. наук, профессор, заведующий кафедрой «Технологии программирования»\n" +
                "национального исследовательского университета информационных технологий, механики и\n" +
                "оптики, лауреат премии Правительства РФ в области образования\n" +
                "\n" +
                "Заметки о мотивации\n" +
                "Текст обо всем, который можно читать с любого места\n" +
                "– Дяденька, зачем Вы стоите здесь и проповедуете\n" +
                "добро? Ведь Вас никто не слушает. Да и разве Вы\n" +
                "в силах что-нибудь изменить? – Все это я и сам\n" +
                "понимаю. Но если я не буду каждый день пытаться\n" +
                "изменить человечество, то боюсь, что оно изменит\n" +
                "меня (Э. Нансен). Я тоже боюсь этого.\n" +
                "\n" +
                "1. Многие считают, что основа мотивации – страх. Страшно остаться без куска хлеба и без крыши над\n" +
                "головой, а еще страшно быть бедным, убитым… Это, конечно, сильный мотиватор, однако он, во-первых,\n" +
                "не определяет поведение тех, кто «работает не за страх, а за совесть», а во-вторых, не является\n" +
                "определяющим для большинства людей, с которыми я постоянно общаюсь, так как они являются\n" +
                "способными и хорошо образованными молодыми людьми, обладающими востребованной во всем мире\n" +
                "специальностью – они программисты. Для многих из них деньги не единственное, что их мотивирует, и\n" +
                "появляются другие мотивы, которые и излагаются ниже.\n" +
                "ффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффф\n" +
                "2. Эти мотивы соответствуют моей жизненной позиции. Мне, конечно, могут напомнить слова лидера\n" +
                "группы «Аукцыон» Л. Федорова: «Твои принципы – ты по ним и живи», но я надеюсь, что они будут\n" +
                "полезны и другим людям, тем более что в «Заметках» Вы услышите различные «голоса». При этом\n" +
                "отмечу, что «других я цитирую для того, чтобы точнее выразить свои мысли» (М. Монтень).\n" +
                "3. В «Заметках» не рассматриваются такие сильные мотиваторы, как секс и любовь, так как,";
        List<String> ans = curtest.bookParse(testString);
    }
}