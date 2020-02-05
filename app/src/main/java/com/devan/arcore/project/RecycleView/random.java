package com.devan.arcore.project.RecycleView;

import com.devan.arcore.project.R;

import java.util.ArrayList;
import java.util.List;

public class random {





    public static final List<randomset> getListItemData()
    {
        List<randomset> listViewItems = new ArrayList<randomset>();
        listViewItems.add(new randomset(1, "Drawing","Draw in AR", R.drawable.material_design_5));
        listViewItems.add(new randomset(2,"Solar System", "Animated",R.drawable.material_design_4));
        listViewItems.add(new randomset(3,"Distance","calculate distance",R.drawable.material_design_5));


        return listViewItems;
    }



    public static class randomset {

        private final int id;
        private final String title;
        private final String subtitle;
        private final int image;

        public randomset(int id, String title, String subtitle, int image) {
            this.id = id;
            this.title = title;
            this.subtitle = subtitle;
            this.image = image;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public int getImage() {
            return image;
        }
    }
}