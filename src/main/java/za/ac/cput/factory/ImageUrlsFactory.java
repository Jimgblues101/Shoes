package za.ac.cput.factory;

import za.ac.cput.domain.ImageUrls;

/**
 * ImageUrlsFactory.java
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 22-Sep-24
 */

public class ImageUrlsFactory {
    public static ImageUrls createImageUrls(
            String imageUrl1,
            String imageUrl2,
            String imageUrl3,
            String imageUrl4
    ) {

        return new ImageUrls.Builder()
                .setImageUrl1(imageUrl1)
                .setImageUrl2(imageUrl2)
                .setImageUrl3(imageUrl3)
                .setImageUrl4(imageUrl4)
                .build();
    }
}
