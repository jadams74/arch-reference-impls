package com.j2911.homebrewapi.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Pojo doubles as data model object and response body. Could have done  this with jsonb but then would require
 * json schema to validate. In this case, the shape of the data never changes. Pojo makes sense here. Not storing an
 * arbitrary object.
 */
public class Recipe {
    private long id;
    private String name;
    private String description;
    private String originalGravity;
    private String finalGravity;
    private String internationalBitternessUnits;
    private String standardReferenceMethod;
    private String alcoholByVolume;
    private String style;
    private String recipeType;
    private String boilTime;
    private List<String> fermentables;
    private List<String> hops;
    private List<String> yeast;
    private List<String> otherIngredients;

    public Recipe(){
        this.fermentables = new ArrayList<String>();
        this.hops = new ArrayList<String>();
        this.yeast = new ArrayList<String>();
        this.otherIngredients = new ArrayList<String>();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOriginalGravity() {
        return originalGravity;
    }

    public void setOriginalGravity(String originalGravity) {
        this.originalGravity = originalGravity;
    }

    public String getFinalGravity() {
        return finalGravity;
    }

    public void setFinalGravity(String finalGravity) {
        this.finalGravity = finalGravity;
    }

    public String getInternationalBitternessUnits() {
        return internationalBitternessUnits;
    }

    public void setInternationalBitternessUnits(String internationalBitternessUnits) {
        this.internationalBitternessUnits = internationalBitternessUnits;
    }

    public String getStandardReferenceMethod() {
        return standardReferenceMethod;
    }

    public void setStandardReferenceMethod(String standardReferenceMethod) {
        this.standardReferenceMethod = standardReferenceMethod;
    }

    public String getAlcoholByVolume() {
        return alcoholByVolume;
    }

    public void setAlcoholByVolume(String alcoholByVolume) {
        this.alcoholByVolume = alcoholByVolume;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getRecipeType() {
        return recipeType;
    }

    public void setRecipeType(String recipeType) {
        this.recipeType = recipeType;
    }

    public String getBoilTime() {
        return boilTime;
    }

    public void setBoilTime(String boilTime) {
        this.boilTime = boilTime;
    }

    public List<String> getFermentables() {
        return fermentables;
    }

    public void setFermentables(List<String> fermentables) {
        this.fermentables = fermentables;
    }

    public List<String> getHops() {
        return hops;
    }

    public void setHops(List<String> hops) {
        this.hops = hops;
    }

    public List<String> getYeast() {
        return yeast;
    }

    public void setYeast(List<String> yeast) {
        this.yeast = yeast;
    }

    public List<String> getOtherIngredients() {
        return otherIngredients;
    }

    public void setOtherIngredients(List<String> otherIngredients) {
        this.otherIngredients = otherIngredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recipe recipe = (Recipe) o;

        if (id != recipe.id) return false;
        if (name != null ? !name.equals(recipe.name) : recipe.name != null) return false;
        if (description != null ? !description.equals(recipe.description) : recipe.description != null) return false;
        if (originalGravity != null ? !originalGravity.equals(recipe.originalGravity) : recipe.originalGravity != null)
            return false;
        if (finalGravity != null ? !finalGravity.equals(recipe.finalGravity) : recipe.finalGravity != null)
            return false;
        if (internationalBitternessUnits != null ? !internationalBitternessUnits.equals(recipe.internationalBitternessUnits) : recipe.internationalBitternessUnits != null)
            return false;
        if (standardReferenceMethod != null ? !standardReferenceMethod.equals(recipe.standardReferenceMethod) : recipe.standardReferenceMethod != null)
            return false;
        if (alcoholByVolume != null ? !alcoholByVolume.equals(recipe.alcoholByVolume) : recipe.alcoholByVolume != null)
            return false;
        if (style != null ? !style.equals(recipe.style) : recipe.style != null) return false;
        if (recipeType != null ? !recipeType.equals(recipe.recipeType) : recipe.recipeType != null) return false;
        if (boilTime != null ? !boilTime.equals(recipe.boilTime) : recipe.boilTime != null) return false;
        if (fermentables != null ? !fermentables.equals(recipe.fermentables) : recipe.fermentables != null)
            return false;
        if (hops != null ? !hops.equals(recipe.hops) : recipe.hops != null) return false;
        if (yeast != null ? !yeast.equals(recipe.yeast) : recipe.yeast != null) return false;
        return otherIngredients != null ? otherIngredients.equals(recipe.otherIngredients) : recipe.otherIngredients == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (originalGravity != null ? originalGravity.hashCode() : 0);
        result = 31 * result + (finalGravity != null ? finalGravity.hashCode() : 0);
        result = 31 * result + (internationalBitternessUnits != null ? internationalBitternessUnits.hashCode() : 0);
        result = 31 * result + (standardReferenceMethod != null ? standardReferenceMethod.hashCode() : 0);
        result = 31 * result + (alcoholByVolume != null ? alcoholByVolume.hashCode() : 0);
        result = 31 * result + (style != null ? style.hashCode() : 0);
        result = 31 * result + (recipeType != null ? recipeType.hashCode() : 0);
        result = 31 * result + (boilTime != null ? boilTime.hashCode() : 0);
        result = 31 * result + (fermentables != null ? fermentables.hashCode() : 0);
        result = 31 * result + (hops != null ? hops.hashCode() : 0);
        result = 31 * result + (yeast != null ? yeast.hashCode() : 0);
        result = 31 * result + (otherIngredients != null ? otherIngredients.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", originalGravity='" + originalGravity + '\'' +
                ", finalGravity='" + finalGravity + '\'' +
                ", internationalBitternessUnits='" + internationalBitternessUnits + '\'' +
                ", standardReferenceMethod='" + standardReferenceMethod + '\'' +
                ", alcoholByVolume='" + alcoholByVolume + '\'' +
                ", style='" + style + '\'' +
                ", recipeType='" + recipeType + '\'' +
                ", boilTime='" + boilTime + '\'' +
                ", fermentables=" + fermentables +
                ", hops=" + hops +
                ", yeast=" + yeast +
                ", otherIngredients=" + otherIngredients +
                '}';
    }
}
