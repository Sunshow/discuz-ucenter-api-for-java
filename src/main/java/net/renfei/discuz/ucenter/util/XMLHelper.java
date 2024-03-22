package net.renfei.discuz.ucenter.util;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;

/**
 * ================================================
 * Discuz! Ucenter API for JAVA
 * ================================================
 * XML工具类，处理UC Client接收到返回结果。
 * UC Client会收到UC Server返回的XML结果
 * 该类将XML中的数据提取成一个List按顺序读取即可。
 * <p>
 * 更多信息：http://code.google.com/p/discuz-ucenter-api-for-java
 * 原作者：梁平 (no_ten@163.com)
 * 创建时间：2009-2-20
 * 更多信息：https://github.com/renfei/discuz-ucenter-api-for-java
 * 修改者：任霏 (i@renfei.net)
 * 修改时间：2020-12-17
 */
public class XMLHelper {

    public static LinkedList<String> ucUnserialize(String input) {
        LinkedList<String> result = new LinkedList<>();

        // 使用 DocumentBuilderFactory 创建 DocumentBuilder 实例
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            // 解析输入
            Document doc = builder.parse(new InputSource(new StringReader(input)));
            NodeList nl = doc.getChildNodes().item(0).getChildNodes();
            int length = nl.getLength();
            for (int i = 0; i < length; i++) {
                if (nl.item(i).getNodeType() == Document.ELEMENT_NODE) {
                    result.add(nl.item(i).getTextContent());
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            // 处理异常情况
            e.printStackTrace();
        }
        return result;
    }
}
