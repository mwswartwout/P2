package p2;

import java.io.File;
import java.io.FileOutputStream;

public class BinaryFileWriter 
{
    public static void writeBinaryFile(String fileName,byte[] b) throws Exception
    {
        FileOutputStream f = new FileOutputStream (new File(fileName));
        f.write(b);
        f.close();
    }
    
    public static void createBinaryFile(String seq, String fileName) throws Exception
    {
        writeBinaryFile(fileName,toByteSequence(seq));
    }
    
    public static byte[] toByteSequence(String data) throws Exception
    {
        int j=-1;
        int byteArrSize = data.length()/8;
        if (data.length()%8 != 0)
        byteArrSize++;
        byte[] byteSeq = new byte[byteArrSize];
        
        for(int i=0; i< data.length(); i++)
        {
            if (i%8 == 0)
            {
                j++;
                byteSeq[j] = 0x00;
            }
            
            byte tmp ;
            
            if (data.charAt(i) == '1')
            {
                tmp = 0x01;
            }
            
            else if (data.charAt(i) == '0') 
            {
                tmp = 0x00;
            }

            else
            {
                throw new Exception ("error in format");
            }
            
            byteSeq[j] = (byte)(tmp | ( byteSeq[j] << 1));
            
        }

        return byteSeq;

    }
}