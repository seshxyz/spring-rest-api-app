package ru.Entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "filetable")
public class FileCell {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String title;
        private String filecontent;
        private String description;
        @Column(name = "createdat")
        @CreationTimestamp()
        private Timestamp createdAt;

        public FileCell() {
        }

        public FileCell(Long id, String title, String filecontent, String description, Timestamp createdAt) {
                this.id = id;
                this.title = title;
                this.filecontent = filecontent;
                this.description = description;
                this.createdAt = createdAt;
        }

        public FileCell(String title, String filecontent, String description) {
                this.title = title;
                this.filecontent = filecontent;
                this.description = description;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public String getFilecontent() {
                return filecontent;
        }

        public void setFilecontent(String file) {
                this.filecontent = file;
        }

        public Timestamp getCreatedAt() {
                return createdAt;
        }

        public Timestamp setCreatedAt(Timestamp createdAt) {
                this.createdAt = createdAt;
                return createdAt;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

}
