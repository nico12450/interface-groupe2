/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package explogit;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 *
 * @author romain
 */
public class zip 
{
    public static void decompress(final File file, final File folder,final boolean deleteZipAfter) throws IOException 
    {
        final ZipInputStream zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(file.getCanonicalFile())));
        ZipEntry ze;
        try {    
	        // Parcourt tous les fichiers
	        while (null != (ze = zis.getNextEntry())) {
	            final File f = new File(folder.getCanonicalPath(), ze.getName());
	            if (f.exists())
	            	f.delete();
	            
	            // Création des dossiers
	            if (ze.isDirectory()) {
	                f.mkdirs();
	                continue;
	            }
	            f.getParentFile().mkdirs();
	            final OutputStream fos = new BufferedOutputStream(
	            		new FileOutputStream(f));
	            
	            // Ecriture des fichiers
	            try {
	                try {
	                    final byte[] buf = new byte[8192];
	                    int bytesRead;
	                    while (-1 != (bytesRead = zis.read(buf)))
	                        fos.write(buf, 0, bytesRead);
	                } finally {
	                    fos.close();
	                }
	            } catch (final IOException ioe) {
	                f.delete();
	                throw ioe;
	            }
	        }
        } finally {
        	zis.close();
        }
        if (deleteZipAfter)
        	file.delete();
    }
	
	public static void decompress(final String fileName, final String folderName,
		final boolean deleteZipAfter)
    throws IOException 
        {
		decompress(new File(fileName), new File(folderName), deleteZipAfter);
	}
	public static void decompress(final String fileName, final String folderName)
    throws IOException 
        {
		decompress(new File(fileName), new File(folderName), false);
	}
	public static void decompress(final File file, final boolean deleteZipAfter)
    throws IOException 
        {
		decompress(file, file.getCanonicalFile().getParentFile(), deleteZipAfter);
	}
	public static void decompress(final String fileName,
		final boolean deleteZipAfter)
    throws IOException 
        {
		decompress(new File(fileName), deleteZipAfter);
	}
	public static void decompress(final File file)
    throws IOException 
        {
		decompress(file, file.getCanonicalFile().getParentFile(), false);
	}
	public static void decompress(final String fileName)
    throws IOException 
        {
		decompress(new File(fileName));
	}
}

