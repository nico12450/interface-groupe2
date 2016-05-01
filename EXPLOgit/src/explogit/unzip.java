package explogit;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.InflaterInputStream;

public  class unzip
{
    public String Decompresser( String filename) throws IOException 
    {
        byte[] uncompressedData = new byte[1024];

        try (InputStream stream = new InflaterInputStream(new FileInputStream(filename))) 
        {
            int len, offset = 0;
            while ((len = stream.read(uncompressedData , offset, uncompressedData .length-offset))>0)
            {
                offset += len;
            }
        }		    
        String string = new String(uncompressedData,"UTF-8");
        String[] split = string.split(" ");	    
        return split[0];    
    }
}
