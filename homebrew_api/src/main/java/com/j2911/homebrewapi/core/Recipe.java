package com.j2911.homebrewapi.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Pojo doubles as data model object and response body. Could have done  this with jsonb but then would require
 * json schema to validate. In this case, the shape of the data never changes. Pojo makes sense here. Not storing an
 * arbitrary object.
 */
public class Recipe {
    private static Logger LOG = LoggerFactory.getLogger(Recipe.class);

    private long id;
    private String createdAt = "";
    private String updatedAt = "";
    private String name = "";
    private String description = "";
    private double originalGravity;
    private double finalGravity;
    private int internationalBitternessUnits;
    private int standardReferenceMethod;
    private double alcoholByVolume;
    private String style = "";
    private String recipeType = "";
    private int boilTime;
    private List<String> fermentables;
    private List<String> hops;
    private List<String> yeast;
    private List<String> otherIngredients;

    public Recipe(){
        this.fermentables = new ArrayList<>();
        this.hops = new ArrayList<>();
        this.yeast = new ArrayList<>();
        this.otherIngredients = new ArrayList<>();
    }

    public Recipe(long id,
                  String createdAt,
                  String updatedAt,
                  String name,
                  String description,
                  double originalGravity,
                  double finalGravity,
                  int internationalBitternessUnits,
                  int standardReferenceMethod,
                  double alcoholByVolume,
                  String style,
                  String recipeType,
                  int boilTime,
                  List<String> fermentables,
                  List<String> hops,
                  List<String> yeast,
                  List<String> otherIngredients) {

        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.name = name;
        this.description = description;
        this.originalGravity = originalGravity;
        this.finalGravity = finalGravity;
        this.internationalBitternessUnits = internationalBitternessUnits;
        this.standardReferenceMethod = standardReferenceMethod;
        this.alcoholByVolume = alcoholByVolume;
        this.style = style;
        this.recipeType = recipeType;
        this.boilTime = boilTime;
        this.fermentables = fermentables;
        this.hops = hops;
        this.yeast = yeast;
        this.otherIngredients = otherIngredients;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
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

    public double getOriginalGravity() {
        return originalGravity;
    }

    public void setOriginalGravity(double originalGravity) {
        this.originalGravity = originalGravity;
    }

    public double getFinalGravity() {
        return finalGravity;
    }

    public void setFinalGravity(double finalGravity) {
        this.finalGravity = finalGravity;
    }

    public int getInternationalBitternessUnits() {
        return internationalBitternessUnits;
    }

    public void setInternationalBitternessUnits(int internationalBitternessUnits) {
        this.internationalBitternessUnits = internationalBitternessUnits;
    }

    public int getStandardReferenceMethod() {
        return standardReferenceMethod;
    }

    public void setStandardReferenceMethod(int standardReferenceMethod) {
        this.standardReferenceMethod = standardReferenceMethod;
    }

    public double getAlcoholByVolume() {
        return alcoholByVolume;
    }

    public void setAlcoholByVolume(double alcoholByVolume) {
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

    public int getBoilTime() {
        return boilTime;
    }

    public void setBoilTime(int boilTime) {
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
        if (Double.compare(recipe.originalGravity, originalGravity) != 0) return false;
        if (Double.compare(recipe.finalGravity, finalGravity) != 0) return false;
        if (internationalBitternessUnits != recipe.internationalBitternessUnits) return false;
        if (standardReferenceMethod != recipe.standardReferenceMethod) return false;
        if (Double.compare(recipe.alcoholByVolume, alcoholByVolume) != 0) return false;
        if (boilTime != recipe.boilTime) return false;
        if (createdAt != null ? !createdAt.equals(recipe.createdAt) : recipe.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(recipe.updatedAt) : recipe.updatedAt != null) return false;
        if (name != null ? !name.equals(recipe.name) : recipe.name != null) return false;
        if (description != null ? !description.equals(recipe.description) : recipe.description != null) return false;
        if (style != null ? !style.equals(recipe.style) : recipe.style != null) return false;
        if (recipeType != null ? !recipeType.equals(recipe.recipeType) : recipe.recipeType != null) return false;
        if (fermentables != null ? !fermentables.equals(recipe.fermentables) : recipe.fermentables != null)
            return false;
        if (hops != null ? !hops.equals(recipe.hops) : recipe.hops != null) return false;
        if (yeast != null ? !yeast.equals(recipe.yeast) : recipe.yeast != null) return false;
        return otherIngredients != null ? otherIngredients.equals(recipe.otherIngredients) : recipe.otherIngredients == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        temp = Double.doubleToLongBits(originalGravity);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(finalGravity);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + internationalBitternessUnits;
        result = 31 * result + standardReferenceMethod;
        temp = Double.doubleToLongBits(alcoholByVolume);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (style != null ? style.hashCode() : 0);
        result = 31 * result + (recipeType != null ? recipeType.hashCode() : 0);
        result = 31 * result + boilTime;
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
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", originalGravity=" + originalGravity +
                ", finalGravity=" + finalGravity +
                ", internationalBitternessUnits=" + internationalBitternessUnits +
                ", standardReferenceMethod=" + standardReferenceMethod +
                ", alcoholByVolume=" + alcoholByVolume +
                ", style='" + style + '\'' +
                ", recipeType='" + recipeType + '\'' +
                ", boilTime=" + boilTime +
                ", fermentables=" + fermentables +
                ", hops=" + hops +
                ", yeast=" + yeast +
                ", otherIngredients=" + otherIngredients +
                '}';
    }
}
