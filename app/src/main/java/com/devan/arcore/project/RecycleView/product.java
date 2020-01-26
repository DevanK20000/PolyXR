package com.devan.arcore.project.RecycleView;

import com.devan.arcore.project.R;

import java.util.ArrayList;
import java.util.List;

public class product {





    public static final List<productset> getProductItemData()
    {
        List<productset> productViewItems = new ArrayList<productset>();
       productViewItems.add(new productset(1, "Leather Club Chair","Office Star Embrace Mocha Eco Leather Club Chair with Cherry Finish Legs", R.drawable.product_1,R.string.description_1,"https://www.amazon.com/Office-Star-Embrace-Leather-Cherry/dp/B005R9HU9S"));
       productViewItems.add(new productset(2,"bench", "bench with food",R.drawable.random1,R.string.description_1,"google.com"));
        return productViewItems;
    }



    public static class productset {

        private final int id;
        private final String title;
        private final String subtitle;
        private final int image;
        private final int description;
        private final String link;


        public productset(int id, String title, String subtitle, int image,int description,String link) {
            this.id = id;
            this.title = title;
            this.subtitle = subtitle;
            this.image = image;
            this.description =description;
            this.link=link;

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

        public int getDescription(){ return description; }

        public String getLink(){ return  link; }

    }
}