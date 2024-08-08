package com.kardoaward.kardo.streams.model;

import lombok.*;

import javax.persistence.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "streams")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stream {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "link")
    private URL link;

    @Column(name = "stream_date_time")
    private LocalDateTime streamDateTime;

    @Column(name = "published_date")
    private LocalDateTime publishedDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stream stream = (Stream) o;
        return Objects.equals(id, stream.id) &&
                Objects.equals(name, stream.name) &&
                Objects.equals(link, stream.link) &&
                Objects.equals(streamDateTime, stream.streamDateTime) &&
                Objects.equals(publishedDate, stream.publishedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, link, streamDateTime, publishedDate);
    }
}
