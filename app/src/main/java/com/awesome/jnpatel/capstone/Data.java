package com.awesome.jnpatel.capstone;

import android.graphics.Color;

/**
 * Created by jnpatel on 7/15/16.
 */
public class Data {
    private String name;
    private String info;
    private int pic;
    private int color;


    public static final Data[] data = {
                    // name of Michigan Cities
            new Data("Detroit",
                    // things to do
                    " - Eminien was born here\n - Its a beautiful place to visit\n - Check out the riverfront restaurants\n",
                    // image of city
                    R.drawable.detroit,
                    // background color for this city
                    Color.parseColor("#FFEBEE")),

            new Data("Grand Rapids",
                    " - Settled by Dutch immigrants\n - Check out the Grand River! \n - Head to Gaslight Village for some great food\n ",
                    R.drawable.grand_rapids,
                    Color.parseColor("#B2EBF2")),

            new Data("Mackinaw Island",
                    " - The all natural theme park of America\n - Limited to transportation of horse & buggy\n - Has escaped the vast changes of time\n",
                    R.drawable.mackinawisland,
                    Color.parseColor("#CFD8DC")),

            new Data("Traverse City",
                    " - Known for local wineries\n - Very quaint\n - Check out Sleeping Bear Dunes!\n - But don't go up the sleeping bear dunes\n  if you can't climb down again!\n",
                    R.drawable.traversecity,
                    Color.parseColor("#B2EBF2")),

            new Data("Ann Arbor",
                    " - Home of U of M & the Big House stadium\n - Known as a craft beer destination\n - Drunk girls all over there after school hours\n",
                    R.drawable.annarbor,
                    Color.parseColor("#B2DFDB")),

            new Data("Lansing",
                    " - State capital of Michigan\n - Home of Western Michigan University\n - Potter Park Zoo is fun & intimate \n - You'll get to see some endangered animals\n",
                    R.drawable.lansing,
                    Color.parseColor("#FFEBEE")),

            new Data("Frankenmuth",
                    " - This city is politically independent\n - Home of Bronner's Christmas Wonderland\n - Festivals year round\n",
                    R.drawable.frankenmuth,
                    Color.parseColor("#B2DFDB")),

            new Data("Dearborn",
                    " - The eighth largest city in Michigan\n - Home of Greenfield Village & \n   Henry Ford Museum\n - Find Professor John Baugh teaching\n   at U of M Dearborn!\n",
                    R.drawable.dearborn,
                    Color.parseColor("#C8E6C9")),

            new Data("Muskegon",
                    " - Off the shores of Lake Michigan\n - Largest populated city on the eastern shores \n   of Lake Michigan\n - Home of Michigan's Adventure Park\n",
                    R.drawable.muskegon,
                    Color.parseColor("#CFD8DC")),

            new Data("Flint",
                    " - Located along the Flint River\n - Bring your own water \n - Check out the Flint Institute of Arts\n ",
                    R.drawable.flint,
                    Color.parseColor("#B2EBF2")),

    };

    //Each city has a name, info, pic and backgroud color
    private Data(String name, String info, int pic, int color) {
        this.name = name;
        this.info = info;
        this.pic = pic;
        this.color = color;
    }
    // these are the methods that will be called in the .java class to display information
    // these methods will return each item from class array above
    public String getInfo() {
        return info;
    }

    public int getColor() {
        return color;
    }

    public int GetPic() {
        return pic;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return this.name;
    }
}
