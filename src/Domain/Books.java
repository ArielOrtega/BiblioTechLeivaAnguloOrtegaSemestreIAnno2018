
package Domain;


public class Books extends Material{
    private String autor;
    private String genre;
    private String language;
    
    
    public Books(String autor, String genre, String language, String name, String signature, int availability, String description) {
        super(name, signature, availability, description);
        this.autor = autor;
        this.genre = genre;
        this.language = language;
    } 
    

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }


    @Override
    public String toString() {
        return super.toString() + "\nLibros{" + "autor=" + autor + ", genero=" + genre + ", idioma=" + language + '}';
    }

    public int sizeInBytes(){
        
        return super.getName().length() * 2 + super.getSignature().length() * 2 + super.getDescription().length() * 2
                + 12 + this.autor.length() * 2 + this.genre.length() * 2 + this.language.length() * 2;
    }
    
}
