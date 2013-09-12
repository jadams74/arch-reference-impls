package com.j2911.homebrewapi.domain;

import com.mongodb.ReflectionDBObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jadams
 * Date: 8/10/13
 * Time: 11:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class Recipe extends ReflectionDBObject {
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

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getOriginalGravity(){
        return this.originalGravity;
    }

    public void setOriginalGravity(String originalGravity){
        this.originalGravity = originalGravity;
    }

    public String getFinalGravity(){
        return this.finalGravity;
    }

    public void setFinalGravity(String finalGravity){
        this.finalGravity = finalGravity;
    }

    public String getInternationalBitternessUnits(){
        return this.internationalBitternessUnits;
    }

    public void setInternationalBitternessUnits(String ibu){
        this.internationalBitternessUnits = ibu;
    }

    public String getStandardReferenceMethod(){
        return this.standardReferenceMethod;
    }

    public void setStandardReferenceMethod(String srm){
        this.standardReferenceMethod = srm;
    }

    public String getAlcoholByVolume(){
        return this.alcoholByVolume;
    }

    public void setAlcoholByVolume(String abv){
        this.alcoholByVolume = abv;
    }

    public String getStyle(){
        return this.style;
    }

    public void setStyle(String style){
        this.style = style;
    }

    public String getRecipeType(){
        return this.recipeType;
    }

    public void setRecipeType(String recipeType){
        this.recipeType = recipeType;
    }

    public String getBoilTime(){
        return this.boilTime;
    }

    public void setBoilTime(String boilTime){
        this.boilTime = boilTime;
    }

    public List<String>getFermentables(){
        return this.fermentables;
    }

    public void setFermentables(List<String>fermentables){
        this.fermentables = fermentables;
    }

    public List<String>getHops(){
        return this.hops;
    }

    public void setHops(List<String>hops){
        this.hops = hops;
    }

    public List<String>getYeast(){
        return this.yeast;
    }

    public void setYeast(List<String>yeast){
        this.yeast = yeast;
    }

    public List<String>getOtherIngredients(){
        return this.otherIngredients;
    }

    public void setOtherIngredients(List<String>otherIngredients){
        this.otherIngredients = otherIngredients;
    }

    @Override
    public String toString(){
        return String.format("Recipe: %s", this.name);
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }

        if(!(obj instanceof Recipe)){
            return false;
        }

        Recipe that = (Recipe)obj;

        if(this.alcoholByVolume.equals(that.alcoholByVolume)
            && this.boilTime.equals(that.boilTime)
            && this.description.equals(that.description)
            && this.fermentables.equals(that.fermentables)
            && this.finalGravity.equals(that.finalGravity)
            && this.hops.equals(that.hops)
            && this.internationalBitternessUnits.equals(that.internationalBitternessUnits)
            && this.name.equals(that.name)
            && this.originalGravity.equals(that.originalGravity)
            && this.otherIngredients.equals(that.otherIngredients)
            && this.recipeType.equals(that.recipeType)
            && this.standardReferenceMethod.equals(that.standardReferenceMethod)
            && this.style.equals(that.style)
            && this.yeast.equals(that.yeast)
            ){
            return true;
        }
        return false;
    }
}
