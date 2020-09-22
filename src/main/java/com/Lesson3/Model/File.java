package com.Lesson3.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "FILES")
public class File {
    public long id;
    public String name;
    public String format;
    public long size;
    public Storage storage;

    public File() {
    }

    public File(long id, String name, String format, long size, Storage storage) {
        this.id = id;
        this.name = name;
        this.format = format;
        this.size = size;
        this.storage = storage;
    }

    @Id
    @SequenceGenerator(name = "F_SEQ", sequenceName = "FILE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "F_SEQ")
    @Column(name = "ID")
    @JsonProperty("id")
    public long getId() {
        return id;
    }

    @Column(name = "NAME")
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @Column(name = "FORMAT")
    @JsonProperty("format")
    public String getFormat() {
        return format;
    }

    @Column(name = "FILE_SIZE")
    @JsonProperty("size")
    public long getSize() {
        return size;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "STORAGE")
    @JsonProperty("storage")
    public Storage getStorage() {
        return storage;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return id == file.id &&
                size == file.size &&
                name.equals(file.name) &&
                format.equals(file.format) &&
                storage.equals(file.storage);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", format='" + format + '\'' +
                ", size=" + size +
                ", storage=" + storage +
                '}';
    }
}
