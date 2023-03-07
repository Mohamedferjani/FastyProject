package edu.fasty.entities;


public class CategoryRating {
    
    //LES ATTRIBUTS
    int id_Cat_Rating;
    String name_Cat_Rating;
    String description_Cat_Rating;
    String logo;
    
    //LES CONSTRUCTEURS

    public CategoryRating() {
    }

    public CategoryRating(String name_Cat_Rating, String description_Cat_Rating, String logo) {
        this.name_Cat_Rating = name_Cat_Rating;
        this.description_Cat_Rating = description_Cat_Rating;
        this.logo = logo;
    }

    public CategoryRating(int id_Cat_Rating, String name_Cat_Rating, String description_Cat_Rating, String logo) {
        this.id_Cat_Rating = id_Cat_Rating;
        this.name_Cat_Rating = name_Cat_Rating;
        this.description_Cat_Rating = description_Cat_Rating;
        this.logo = logo;
    }
    
    //GETTERS ET SETTERS

    public int getId_Cat_Rating() {
        return id_Cat_Rating;
    }

    public void setId_Cat_Rating(int id_Cat_Rating) {
        this.id_Cat_Rating = id_Cat_Rating;
    }

    public String getName_Cat_Rating() {
        return name_Cat_Rating;
    }

    public void setName_Cat_Rating(String name_Cat_Rating) {
        this.name_Cat_Rating = name_Cat_Rating;
    }

    public String getDescription_Cat_Rating() {
        return description_Cat_Rating;
    }

    public void setDescription_Cat_Rating(String description_Cat_Rating) {
        this.description_Cat_Rating = description_Cat_Rating;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
    
    //TO STRING , HACHCODE AND EQUALS

    @Override
    public String toString() {
        return  "name_Cat_Rating=" + name_Cat_Rating + ", description_Cat_Rating=" + description_Cat_Rating + ", logo=" + logo ;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CategoryRating other = (CategoryRating) obj;
        if (this.id_Cat_Rating != other.id_Cat_Rating) {
            return false;
        }
        return true;
    }
    
    
    
}
