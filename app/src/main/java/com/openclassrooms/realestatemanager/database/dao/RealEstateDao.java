package com.openclassrooms.realestatemanager.database.dao;

import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Update;
import androidx.sqlite.db.SupportSQLiteQuery;

import com.openclassrooms.realestatemanager.model.RealEstate;

import java.util.Date;
import java.util.List;

@Dao
public interface RealEstateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertRealEstate(RealEstate realEstate);


    @Query("SELECT * FROM RealEstate WHERE id = :realEstateId")
    LiveData<RealEstate> getRealEstate(long realEstateId);

    @Query("SELECT * FROM RealEstate")
    LiveData<List<RealEstate>> getRealEstates();

    @Update
    int updateRealEstate(RealEstate realEstate);

    @Query("DELETE FROM RealEstate")
    int deleteAll();

    //Content Provider
    @Query("SELECT * FROM RealEstate")
    Cursor getRealEstatesWithCursor();

    //Filter
    @RawQuery(observedEntities = RealEstate.class)
    LiveData<List<RealEstate>> getRealEstatesFiltered(SupportSQLiteQuery query);


}
