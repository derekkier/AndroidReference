package com.derekkier.androidreference;

import java.io.Serializable;

/**
 * Created by Toshiba on 9/11/2015.
 */
public class SharedObject implements Serializable {

    public String[] arrSeats =
            {
                    "Seat 1",
                    "Seat 2",
                    "Seat 3",
                    "Seat 4",
                    "Seat 5",
                    "Seat 6",
                    "Seat 7",
                    "Seat 8",
                    "Seat 9",
                    "Seat 10",
                    "Seat 11"
            };

    private int selectedSeat;

    public SharedObject()
    {

    }

    public int getSelectedSeatIndex()
    {
       return selectedSeat;
    }

    public String getSelectedSeat()
    {
        return arrSeats[selectedSeat];
    }

    public void setSelectedSeat( int intSeat )
    {
        this.selectedSeat=intSeat;
    }
}
