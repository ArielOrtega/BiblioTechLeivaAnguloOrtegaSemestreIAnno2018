/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Domain.Books;
import File.BooksFile;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pablo
 */
public class DomainTest {
    
    public static BooksFile booksFile;
    public static Books b1, b2, b3;
    
    public DomainTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws IOException{
        File fileBooks = new File("./Books.dat");
        
        //instanciar estudiantes
        
        b1 = new Books("Jose Ulate", "Terror", "Espannol","Las sombras del ayer","BT102501",4,"Libro de la saga de terror sombras de Jose Ulate");
        b2 = new Books("Karla Orozco", "Fantasia", "Espannol","Viajando a Saturno","BT105602",8,"Cuenta las aventuras de unos jovenes que usan su nave para viajar en el universo");
        b3 = new Books("John Waslin", "Drama", "Ingles","Dry memories","BT205896",14,"Histories of a old man with a broken heart");
        
        booksFile = new BooksFile(fileBooks);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
     public void hello() {
     
         
        Books b2 = new Books("Karla Orozco", "Fantasia", "Espannol","Viajando a Saturno","BT105602",8,"Cuenta las aventuras de unos jovenes que usan su nave para viajar en el universo");
        
        System.out.println(b2.toString());
        System.out.println(b2.sizeInBytes());
     }
    
    //@Test
    public void insertIntoFile(){
        try {
            //insertar en el archivo
            booksFile.addEndRecord(b1);
            booksFile.addEndRecord(b2);
            booksFile.addEndRecord(b3);
        } catch (IOException ex) {
            Logger.getLogger(DomainTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void readPosition() throws IOException{
        Books bookTemp = booksFile.getBook(0);
        System.out.println(bookTemp.toString());
    }
}
