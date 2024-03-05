package Bai31;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.util.ArrayList;
import java.util.Scanner;

public class DSBatDongSan {
    ArrayList<CBatDongSan> ds = new ArrayList<CBatDongSan>();

    public void nhap() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập số lượng:");
        int n = scanner.nextInt();

        for (int i = 0; i<n; i++)
        {
            System.out.print("Nhập loại: ");
            int loai = scanner.nextInt();

            System.out.print("Nhập mã: ");
            String ma = scanner.next();

            System.out.print("Nhập chiều dài: ");
            double dai = scanner.nextDouble();

            System.out.print("Nhập chiều rộng: ");
            double rong = scanner.nextDouble();

            CBatDongSan bds;
            if (loai == 1) {
                System.out.print("Nhập số lầu: ");
                int so = scanner.nextInt();
                bds = new CNhaO(ma, dai, rong, so);
            } else if (loai == 2) {
                bds = new CDatTrong(ma, dai, rong);
            } else if (loai == 3) {
                bds = new CBietThu(ma, dai, rong);
            } else {
                System.out.print("Nhập số sao: ");
                int so = scanner.nextInt();
                bds = new CKhachSan(ma, dai, rong, so);
            }
            ds.add(bds);
        }
//        try {
//            File inputFile = new File(file);
//            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//            Document doc = dBuilder.parse(inputFile);
//            doc.getDocumentElement().normalize();
//            NodeList nodeList = doc.getElementsByTagName("BDS");
//
//            for (int temp = 0; temp < nodeList.getLength(); temp++) {
//                Node node = nodeList.item(temp);
//                if (node.getNodeType() == Node.ELEMENT_NODE) {
//                    Element element = (Element) node;
//                    int loai = Integer.parseInt(element.getElementsByTagName("Loai").item(0).getTextContent());
//                    String ma = element.getElementsByTagName("MaSo").item(0).getTextContent();
//                    double dai = Double.parseDouble(element.getElementsByTagName("ChieuDai").item(0).getTextContent());
//                    double rong = Double.parseDouble(element.getElementsByTagName("ChieuRong").item(0).getTextContent());
//
//                    CBatDongSan bds;
//                    if (loai == 1) {
//                        int so = Integer.parseInt(element.getElementsByTagName("So").item(0).getTextContent());
//                        bds = new CNhaO(ma, dai, rong, so);
//                    } else if (loai == 2) {
//                        bds = new CDatTrong(ma, dai, rong);
//                    } else if (loai == 3) {
//                        bds = new CBietThu(ma, dai, rong);
//                    } else {
//                        int so = Integer.parseInt(element.getElementsByTagName("So").item(0).getTextContent());
//                        bds = new CKhachSan(ma, dai, rong, so);
//                    }
//                    ds.add(bds);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public void xuat() {
        for (CBatDongSan bds : ds) {
            bds.xuat();
        }
    }

    public double TongGiaTri() {
        double tong = 0;
        for (CBatDongSan bds : ds) {
            tong += bds.giaBatDongSan();
        }
        return tong;
    }

    public double TongThue() {
        double tong = 0;
        for (CBatDongSan bds : ds) {
            if (bds instanceof IPhiKinhDoanh) {
                IPhiKinhDoanh t = (IPhiKinhDoanh) bds;
                tong += t.tinhPhiKD();
            }
        }
        return tong;
    }
}
