package sky.pro.socks_warehouse.service.imp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sky.pro.socks_warehouse.dto.SocksCount;
import sky.pro.socks_warehouse.dto.SocksCreate;
import sky.pro.socks_warehouse.exception_nandler.ResourceNotFoundException;
import sky.pro.socks_warehouse.mappers.SocksMapper;
import sky.pro.socks_warehouse.model.Socks;
import sky.pro.socks_warehouse.model.SocksId;
import sky.pro.socks_warehouse.repository.CustomRepository;
import sky.pro.socks_warehouse.repository.SocksRepository;
import sky.pro.socks_warehouse.service.SocksService;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SockServiceImp implements SocksService {

    private final SocksRepository socksRepository;

    private final CustomRepository customRepository;

    private final SocksMapper mapper;

    private static Map<String, String> symbols;

    static {
        symbols = Map.of(
                "moreThan", ">",
                "lessThan", "<",
                "equal", "="
        );
    }

    @Override
    @Transactional
    public void createSocks(SocksCreate socksCreate) {
        SocksId socksId = mapper.toSocksId(socksCreate);
        Optional<Socks> socksOrEmpty = socksRepository.findById(socksId);
        Socks socks = getSocksForCreate(socksCreate, socksOrEmpty, socksId);
        socksRepository.save(socks);
    }


    @Override
    @Transactional
    public void deleteSocks(SocksCreate socksCreate) {
        SocksId socksId = mapper.toSocksId(socksCreate);
        Optional<Socks> socksOrEmpty = socksRepository.findById(socksId);
        Socks socks = getSocksForDelete(socksCreate, socksOrEmpty);
        socksRepository.save(socks);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getPartSocks(SocksCount socksCount) {
        return customRepository.getCountPartSocks(
                socksCount.getColor(),
                symbols.get(socksCount.getOperation()),
                socksCount.getCottonPart()
        );
    }

    private Socks getSocksForCreate(SocksCreate socksCreate, Optional<Socks> socksOrEmpty, SocksId id) {
        if (socksOrEmpty.isEmpty()) {
            return new Socks(id, socksCreate.getQuantity());
        }
        Socks socks = socksOrEmpty.get();
        Integer quantity = socks.getQuantity() + socksCreate.getQuantity();
        socks.setQuantity(quantity);
        return socks;
    }

    private Socks getSocksForDelete(SocksCreate socksCreate, Optional<Socks> socksOrEmpty) {
        if (socksOrEmpty.isEmpty()) {
            throw new ResourceNotFoundException("Данный тип носков в базе не сущестует");
        }
        Socks socks = socksOrEmpty.get();
        if (socks.getQuantity() < socksCreate.getQuantity()) {
            throw new ResourceNotFoundException("Количество меньше запрашиваемого и = " + socks.getQuantity());
        }
        Integer quantity = socks.getQuantity() - socksCreate.getQuantity();
        socks.setQuantity(quantity);
        return socks;
    }
}
