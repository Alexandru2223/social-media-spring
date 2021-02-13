package com.pibd.project1.service;

import com.pibd.project1.domain.entity.ArtistEntity;
import com.pibd.project1.domain.entity.LikesEntity;
import com.pibd.project1.domain.entity.PaintingEntity;
import com.pibd.project1.domain.model.LikesDTO;
import com.pibd.project1.mapper.ArtistEntityToArtistDTOMapper;
import com.pibd.project1.mapper.ArtistEntityToArtistDTOWithoutPaintingMapper;
import com.pibd.project1.mapper.LikesEntityToLikesDTOWithoutArtist;
import com.pibd.project1.mapper.PaintingEntityToPainterDTOMappetWithArtist;
import com.pibd.project1.repository.ArtistRepository;
import com.pibd.project1.repository.LikesRepository;
import com.pibd.project1.repository.PaintingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LikeService {

    private final LikesRepository likesRepository;

    private final ArtistRepository artistRepository;

    private final PaintingRepository paintingRepository;

    private final PaintingEntityToPainterDTOMappetWithArtist paintingEntityToPainterDTOMappetWithArtist;

    private final ArtistEntityToArtistDTOWithoutPaintingMapper artistEntityToArtistDTOWithoutPaintingMapper;

    private final LikesEntityToLikesDTOWithoutArtist likesEntityToLikesDTOWithoutArtist;

    private final ArtistEntityToArtistDTOMapper artistEntityToArtistDTOMapper;

    private final PaintingService paintingService;


    @Transactional
    public void giveLike(long id, String email) {

        PaintingEntity paintingEntity = paintingRepository.findById(id).get();
        ArtistEntity artistEntity = artistRepository.getArtistEntityByEmail(email);


        ArtistEntity artistAndLikes = artistRepository.getArtistAndLikes(email);



        if(artistAndLikes != null) {
            List<LikesDTO> likes = artistEntityToArtistDTOMapper.convert(artistAndLikes).getLikes();
            for(LikesDTO like: likes){
                if (like.getArtistId() == artistEntity.getArtistId() && like.getPaintingId() == id){
                    getLikeBack(id, email);
                    return;
                }
            }
        }

        paintingService.getLikes(id);

        ArtistEntity artistEntity1 = ArtistEntity.builder()
                .artistId(artistEntity.getArtistId())
                .avatar(artistEntity.getAvatar())
                .born(artistEntity.getBorn())
                .country(artistEntity.getCountry())
                .fullName(artistEntity.getFullName())
                .email(artistEntity.getEmail())
                .build();

        LikesEntity likesEntity = LikesEntity.builder()
                .artistId(artistEntity1.getArtistId())
                .paintingId(paintingEntity.getPaintingId())
                .artistEntity(artistEntity1)
                .build();

        likesRepository.save(likesEntity);

    }


    @Transactional
    public void getLikeBack(long id, String email){
        ArtistEntity artistEntity = artistRepository.getArtistEntityByEmail(email);
        List<LikesEntity> collect = artistEntity.getLikesEntities().stream().filter(x -> x.getPaintingId() == id).collect(Collectors.toList());
        likesRepository.deleteLike(collect.get(0).getId());
        paintingService.dropLikes(id);

    }
}
