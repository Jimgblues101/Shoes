package za.ac.cput.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

/**
 * ImageUrls.java
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 20-Sep-24
 */
@Getter
@Embeddable
public class ImageUrls {

    private String imageUrl1;
    private String imageUrl2;
    private String imageUrl3;
    private String imageUrl4;

    public ImageUrls() {}

    private ImageUrls(Builder builder) {
        this.imageUrl1 = builder.imageUrl1;
        this.imageUrl2 = builder.imageUrl2;
        this.imageUrl3 = builder.imageUrl3;
        this.imageUrl4 = builder.imageUrl4;
    }


    @Override
    public String toString() {
        return "ImageUrls{" +
                "imageUrl1='" + imageUrl1 + '\'' +
                ", imageUrl2='" + imageUrl2 + '\'' +
                ", imageUrl3='" + imageUrl3 + '\'' +
                ", imageUrl4='" + imageUrl4 + '\'' +
                '}';
    }

    public static class Builder {
        private String imageUrl1;
        private String imageUrl2;
        private String imageUrl3;
        private String imageUrl4;

        public Builder setImageUrl1(String imageUrl1) {
            this.imageUrl1 = imageUrl1;
            return this;
        }

        public Builder setImageUrl2(String imageUrl2) {
            this.imageUrl2 = imageUrl2;
            return this;
        }

        public Builder setImageUrl3(String imageUrl3) {
            this.imageUrl3 = imageUrl3;
            return this;
        }

        public Builder setImageUrl4(String imageUrl4) {
            this.imageUrl4 = imageUrl4;
            return this;
        }

        public Builder copy(ImageUrls imageUrls) {
            this.imageUrl1 = imageUrls.imageUrl1;
            this.imageUrl2 = imageUrls.imageUrl2;
            this.imageUrl3 = imageUrls.imageUrl3;
            this.imageUrl4 = imageUrls.imageUrl4;
            return this;
        }

        public ImageUrls build() {
            return new ImageUrls(this);
        }
    }
}
