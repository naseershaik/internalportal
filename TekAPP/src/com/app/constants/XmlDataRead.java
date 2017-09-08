package com.app.constants;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.app.service.DBConnect;

public class XmlDataRead {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			File file = new File("C:\\Users\\nshaik\\Desktop\\xmlData2.xml");

			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
		                             .newDocumentBuilder();

			Document doc = dBuilder.parse(file);

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			if (doc.hasChildNodes()) {

				printNote(doc.getChildNodes());

			}

		    } catch (Exception e) {
			System.out.println(e.getMessage());
		    }

		  }

		  private static void printNote(NodeList nodeList) {
				Connection conn;
				PreparedStatement stmt,stmt1;
				ResultSet rs;
				DBConnect db=DBConnect.getDbCon();
				String nodename;
				String attrname;
		    for (int count = 0; count < nodeList.getLength(); count++) {

			Node tempNode = nodeList.item(count);

			// make sure it's element node.
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

				// get node name and value
				System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
				System.out.println("Node Value =" + tempNode.getTextContent());
				 nodename=tempNode.getNodeName();
				 System.out.println("ndname---"+nodename);
				 try {
					stmt1=db.conn.prepareStatement("insert into XMLDATAREAD values(?,?,?)");
					stmt1.setString(1, nodename);
					stmt1.setString(2, null);
					stmt1.setString(3, null);
					/*stmt1.executeUpdate();*/
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (tempNode.hasAttributes()) {

					// get attributes names and values
					NamedNodeMap nodeMap = tempNode.getAttributes();

					for (int i = 0; i < nodeMap.getLength(); i++) {
						
						Node node = nodeMap.item(i);
						System.out.println("attr name : " + node.getNodeName());
						attrname=node.getNodeName();
						System.out.println("atname--"+attrname);
						System.out.println("attr value : " + node.getNodeValue());
						try {
							stmt=db.conn.prepareStatement("insert into XMLDATAREAD values(?,?,?)");
							stmt.setString(1, null);
							stmt.setString(2, attrname);
							stmt.setString(3, null);
							/*stmt.executeUpdate();*/
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							System.out.println(e.getMessage());
						}

					}

				}

				if (tempNode.hasChildNodes()) {

					// loop again if has child nodes
					printNote(tempNode.getChildNodes());

				}

				System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");

			}

		    }

		  }

}
