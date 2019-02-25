package lambda.refactoring.vo;

import java.util.List;
import java.util.function.ToLongFunction;

public class Order {
    private List<Album> albums;

    //使用命令式
    public long countRunningTime(){
        long count = 0;
        for (Album album: albums
             ) {
            for (Track track: album.getTrackList()
                 ) {
                count += track.getLength();
            }
        }
        return count;
    }


    public long countMusicians(){
        long count =0;
        for (Album album : albums){
            count += album.getMusicianList().size();
        }
        return count;
    }

    public long countTracks(){
        long count = 0;
        for (Album album : albums){
            count += album.getTrackList().size();
        }
        return count;
    }

    //流⬇️
    public long countRunningTimeByStream(){
        return albums.stream()
                .mapToLong(album -> album.getTracks()
                                         .mapToLong(track -> track.getLength())
                                         .sum())
                .sum();

    }

    public long countMusiciansByStream(){
        return albums.stream()
                .mapToLong(album -> album.getMusicians().count())
                .sum();
    }

    public long countTracksByStream(){
        return albums.stream()
                .mapToLong(album -> album.getTracks().count())
                .sum();
    }

    //使用领域方法重构⬇️
    public long countFeature(ToLongFunction<Album> function){
        return albums.stream()
                .mapToLong(function)
                .sum();
    }

    public long countTracksFix(){
        return countFeature(album -> album.getTracks().count());
    }

    public long countRunningTimeFix(){
        return countFeature(album -> album.getTracks()
                                          .mapToLong(track -> track.getLength())
                                          .sum());
    }

    public long countMusiciansFix(){
        return countFeature(album -> album.getMusicians().count());
    }
}
