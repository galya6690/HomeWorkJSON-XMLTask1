package com.gmail.galya6690;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Main {

	public static void main(String[] args) {
		String[] towns = { "Kiev", "Lviv", "Odessa", "Kharkiv", "Khmelnytskyi", "Ternopil" };
		Random random = new Random();
		File file = new File("C://Users//Galinka//Desktop//mydoc//DOMXML.xml");
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();

			Element rootElement = document.createElement("trains");
			document.appendChild(rootElement);
			// train1
			Element train1 = document.createElement("train1");
			train1.setAttribute("id", "1");
			rootElement.appendChild(train1);

			Element from = document.createElement("from");
			from.setTextContent(towns[random.nextInt(towns.length)]);
			train1.appendChild(from);

			Element to = document.createElement("to");
			to.setTextContent(towns[random.nextInt(towns.length)]);
			train1.appendChild(to);

			Element data = document.createElement("date");
			data.setTextContent(LocalDate.now().toString());
			train1.appendChild(data);

			Element time = document.createElement("time");
			time.setTextContent(LocalTime.of(random.nextInt(24), random.nextInt(60), random.nextInt(60)).toString());
			train1.appendChild(time);

			// train2
			Element train2 = document.createElement("train2");
			train2.setAttribute("id", "2");
			rootElement.appendChild(train2);

			Element from2 = document.createElement("from");
			from2.setTextContent(towns[random.nextInt(towns.length)]);
			train2.appendChild(from2);

			Element to2 = document.createElement("to");
			to2.setTextContent(towns[random.nextInt(towns.length)]);
			train2.appendChild(to2);

			Element data2 = document.createElement("date");
			data2.setTextContent(LocalDate.now().toString());
			train2.appendChild(data2);

			Element time2 = document.createElement("time");
			time2.setTextContent(LocalTime.of(random.nextInt(24), random.nextInt(60), random.nextInt(60)).toString());
			train2.appendChild(time2);

			// Save

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(file);
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//
		sampleTime(file, 10, 20);

	}

	public static void sampleTime(File file, int fromTime,int toTime) {
		boolean check = true;
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(file);

			Element root = document.getDocumentElement();
			System.out.println("root= " + root.getNodeName());
			System.out.println("----------------------");

			NodeList nodeList = root.getChildNodes();

			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;

					LocalTime time = LocalTime
							.parse(element.getElementsByTagName("time").item(0).getChildNodes().item(0).getNodeValue());
					if (time.getHour() >= fromTime && time.getHour() <= toTime) {
						System.out.println("сьогодн≥ з "+fromTime+ " до "+ toTime+" в≥дправл€Їтьс€ пот€г ");
						
						System.out.println(node.getNodeName());
								
						System.out.println("from: "
								+ element.getElementsByTagName("from").item(0).getChildNodes().item(0).getNodeValue());

						System.out.println("to: "
								+ element.getElementsByTagName("to").item(0).getChildNodes().item(0).getNodeValue());
						System.out.println("date: "
								+ element.getElementsByTagName("date").item(0).getChildNodes().item(0).getNodeValue());
						System.out.println("time: "
								+ element.getElementsByTagName("time").item(0).getChildNodes().item(0).getNodeValue());
						System.out.println("----------------------");
						check = false;
					}

				}
			}

			if (check) {
				System.out.println("немаЇ пот€г≥в €к≥ в≥дправл€ютьс€ сьогодн≥ з "+fromTime+" до "+toTime);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
