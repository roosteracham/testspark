package basic.image;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Test {

    public static void main(String[] args) throws Exception {
        File jpegFile = new File("F:/Photos/信工-120401班文件/DJI_0145.jpg");
        System.out.println(printImageTags(jpegFile));
    }
    private static String printImageTags(File file) throws ImageProcessingException, Exception{
        Metadata metadata = ImageMetadataReader.readMetadata(file);
        String str=new String();
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                String tagName = tag.getTagName();  //标签名
                String desc = tag.getDescription(); //标签信息

                if (tagName.equals("Image Height")) {
                    str+="Image Height : "+desc+"<br/>";
                    System.out.println("图片高度: "+desc);
                } else if (tagName.equals("Image Width")) {
                    str+="Image Width : "+desc+"<br/>";
                    System.out.println("图片宽度: "+desc);
                } else if (tagName.equals("Date/Time Original")) {
                    System.out.println("拍摄时间: "+desc);
                }else if (tagName.equals("GPS Latitude")) {
                    str+="Latitude : "+pointToLatlong(desc)+"&nbsp&nbsp&nbsp&nbsp"+desc+"<br/>";
                    System.err.println("纬度 : "+desc);
//                	System.err.println("纬度(度分秒格式) : "+pointToLatlong(desc));
                } else if (tagName.equals("GPS Longitude")) {
                    str+="Longitude : "+pointToLatlong(desc)+"&nbsp&nbsp&nbsp&nbsp"+desc+"<br/>";
                    System.err.println("经度: "+desc);
//                	System.err.println("经度(度分秒格式): "+pointToLatlong(desc));
                }
            }
        }
        return str;
    }
    /**
     * 经纬度格式  转换为  度分秒格式 ,如果需要的话可以调用该方法进行转换
     * @param point 坐标点
     * @return
     */
    public static String pointToLatlong (String point ) {
        Double du = Double.parseDouble(point.substring(0, point.indexOf("°")).trim());
        Double fen = Double.parseDouble(point.substring(point.indexOf("°")+1, point.indexOf("'")).trim());
        Double miao = Double.parseDouble(point.substring(point.indexOf("'")+1, point.indexOf("\"")).trim());
        Double duStr = du + fen / 60 + miao / 60 / 60 ;
        return duStr.toString();
    }

}
