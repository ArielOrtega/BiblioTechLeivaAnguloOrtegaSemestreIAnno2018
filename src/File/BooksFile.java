/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import Domain.Books;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 *
 * @author Pablo
 */
public class BooksFile {
    
    //atributos
    public RandomAccessFile randomAccessFile;
    private int regsQuantity;//cantidad de registros en el archivo
    private int regSize;//tamanno del registro
    private String myFilePath;//ruta
    
    public BooksFile(File file) throws IOException{
        //almaceno la ruta
        myFilePath = file.getPath();
        
        //tamanno maximo
        this.regSize = 200;
        
        //Asegurarse de que el archivo existe
        if(file.exists() && !file.isFile()){
            throw new IOException(file.getName() + " es un archivo invalido");
        }else{
            //crear la nueva instancia de RAF rw= leer y escribir
            randomAccessFile = new RandomAccessFile(file, "rw");
            
            //necesitamos indicar cuantos registros tiene el archivo
            this.regsQuantity = 
                    (int)Math.ceil((double)randomAccessFile.length() / (double)regSize);
        }//fin else
    }//fin metodo
    
    //cerrar el archivo
    public void close() throws IOException{
        randomAccessFile.close();
        
    }
    
    //establecer la cantidad de registros del archivo 
    public int fileSize(){
        return this.regsQuantity;
    }
    
    //insertar un registro de clase hija libro
    public boolean putValue(int position, Books book) throws IOException{
        //primero: verificar que sea valida la insercion
        if(position < 0 && position > this.regsQuantity){
            System.err.println("1001 - Record position is out of bounds");
            return false;
        }else{
            if(book.sizeInBytes() > this.regSize){
                System.err.println("1002 - Record size is out of bounds");
                return false;
            }else{
                
                randomAccessFile.seek(position * this.regSize);
                randomAccessFile.writeUTF(book.getAutor());
                randomAccessFile.writeUTF(book.getGenre());
                randomAccessFile.writeUTF(book.getLanguage());
                randomAccessFile.writeUTF(book.getName());
                randomAccessFile.writeUTF(book.getSignature());
                randomAccessFile.writeInt(book.getAvailability());
                randomAccessFile.writeUTF(book.getDescription());
                return true;
            }
        }
    }
    
    //insertar al final del archivo
    public boolean addEndRecord(Books book) throws IOException{
        boolean success = putValue(this.regsQuantity, book);
        if(success){
            ++this.regsQuantity;
        }
        return success;
    }
    
    //obtener un libro
    public Books getBook(int position) throws IOException{
        //validar la posicion
        if(position >= 0 && position <= this.regsQuantity){
            //colocamos el brazo en el lugar adecuado
            randomAccessFile.seek(position * this.regSize);
            
            //llevamos a cabo la lectura
            Books bookTemp = new Books("","","","","",0,"");
            bookTemp.setAutor(randomAccessFile.readUTF());
            bookTemp.setGenre(randomAccessFile.readUTF());
            bookTemp.setLanguage(randomAccessFile.readUTF());
            bookTemp.setName(randomAccessFile.readUTF());
            bookTemp.setSignature(randomAccessFile.readUTF());
            bookTemp.setAvailability(randomAccessFile.readInt());
            bookTemp.setDescription(randomAccessFile.readUTF());
            
            if(bookTemp.getName().equalsIgnoreCase("deleted")){
                return null;
            }else{
                return bookTemp;
            }
        }else{
            System.err.println("1003 - position is out of bounds");
            return null;
        }
    }//end method
    
    //eliminar un libro
    public boolean deleteBook(String name) throws IOException{
        Books myBook;
        
        //buscar el libro
        for(int i = 0; i < this.regsQuantity; i++){
            //obtener el estudiante de la posici'on actual
            myBook= this.getBook(i);
            
            //preguntar si es el libro que deseo eliminar
            if(myBook.getName().equalsIgnoreCase(name)){
                //marcar como eliminado con bandera
                myBook.setName("deleted");
                
                return this.putValue(i, myBook);
            }
        }
        return false;
    }
    
    //retornar una lista de libros
    public ArrayList<Books> getAllBooks() throws IOException{
        ArrayList<Books> booksArray = new ArrayList<Books>();
        
        for(int i = 0; i < this.regsQuantity; i++){
            Books bTemp = this.getBook(i);
            
            if(bTemp != null){
                booksArray.add(bTemp);
            }
        }//end for
        return booksArray;
    }
}