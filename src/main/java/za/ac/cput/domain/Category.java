package za.ac.cput.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a categories entry in the system.
 *
 * This entity class is mapped to the "categories" table in the database.
 *
 * @author Rethabile Ntsekhe
 * @date 25-Aug-24
 */

@Entity
@Getter
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;

    public Category() {}

    private Category(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.createdAt = builder.createdAt;
        this.deletedAt = builder.deletedAt;
    }
    @Override
    public String toString() {
        return "\n Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", deletedAt=" + deletedAt +
                "}\n ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) &&
                Objects.equals(name, category.name) &&
                Objects.equals(description, category.description) &&
                Objects.equals(createdAt, category.createdAt) &&
                Objects.equals(deletedAt, category.deletedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, createdAt, deletedAt);
    }

    public static class Builder {
        private Long id;
        private String name;
        private String description;
        private LocalDateTime createdAt;
        private LocalDateTime deletedAt;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder setDeletedAt(LocalDateTime deletedAt) {
            this.deletedAt = deletedAt;
            return this;
        }

        public Builder copy(Category category) {
            this.id = category.getId();
            this.name = category.getName();
            this.description = category.getDescription();
            this.createdAt = category.getCreatedAt();
            this.deletedAt = category.getDeletedAt();
            return this;
        }

        public Category build() {
            return new Category(this);
        }
    }
}