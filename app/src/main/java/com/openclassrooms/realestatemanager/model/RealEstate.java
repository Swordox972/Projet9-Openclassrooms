package com.openclassrooms.realestatemanager.model;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.ArrayList;

public class RealEstate implements Serializable {

    @NonNull
    private Type type;
    private String price;
    private int surface;
    private int numberOfRooms;
    private int numberOfBathrooms;
    private int numberOfBedrooms;
    private String description;
    private String photoUrl;
    private ArrayList photos;
    private String firstLocation;
    private String secondLocation;
    private double latitude;
    private double longitude;
    @NonNull
    private Status status;
    private String entryDate;
    private String dateOfSale;
    private Agent agent;
    private String agentPhotoUrl;

    public RealEstate(@NonNull Type type, String price, int surface, int numberOfRooms,
                      int numberOfBathrooms, int numberOfBedrooms, String description, String photoUrl,
                      ArrayList photos, String firstLocation, String secondLocation, double latitude,
                      double longitude, @NonNull Status status, String entryDate, String dateOfSale,
                      Agent agent, String agentPhotoUrl) {
        this.type = type;
        this.price = price;
        this.surface = surface;
        this.numberOfRooms = numberOfRooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.numberOfBedrooms = numberOfBedrooms;
        this.description = description;
        this.photoUrl = photoUrl;
        this.photos = photos;
        this.firstLocation = firstLocation;
        this.secondLocation = secondLocation;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
        this.entryDate = entryDate;
        this.dateOfSale = dateOfSale;
        this.agent = agent;
        this.agentPhotoUrl = agentPhotoUrl;
    }

    @NonNull
    public Type getType() {
        return type;
    }

    public void setType(@NonNull Type type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getSurface() {
        return surface;
    }

    public void setSurface(int surface) {
        this.surface = surface;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public int getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public void setNumberOfBathrooms(int numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public int getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public void setNumberOfBedrooms(int numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public ArrayList getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList photos) {
        this.photos = photos;
    }

    public String getFirstLocation() {
        return firstLocation;
    }

    public void setFirstLocation(String firstLocation) {
        this.firstLocation = firstLocation;
    }

    public String getSecondLocation() {
        return secondLocation;
    }

    public void setSecondLocation(String secondLocation) {
        this.secondLocation = secondLocation;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @NonNull
    public Status getStatus() {
        return status;
    }

    public void setStatus(@NonNull Status status) {
        this.status = status;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getDateOfSale() {
        return dateOfSale;
    }

    public void setDateOfSale(String dateOfSale) {
        this.dateOfSale = dateOfSale;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public String getAgentPhotoUrl() {
        return agentPhotoUrl;
    }

    public void setAgentPhotoUrl(String agentPhotoUrl) {
        this.agentPhotoUrl = agentPhotoUrl;
    }

    public enum Type {
        Flat,
        House,
        Penthouse,
        Duplex,
        Loft
    }

    public enum Status {
        forSell,
        sold
    }

    public enum Agent {
        jessicaCCampbell,
        christianHaag
    }
}

