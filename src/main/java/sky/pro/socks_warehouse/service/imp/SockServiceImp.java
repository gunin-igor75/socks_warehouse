package sky.pro.socks_warehouse.service.imp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
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
        if (socksOrEmpty.isEmpty()) {
            socksRepository.save(new Socks(socksId, socksCreate.getQuantity()));
            log.info("{} Сохранен в базе данных", socksCreate);
        } else {
            Socks socks = socksOrEmpty.get();
            socks.setQuantity(socks.getQuantity() + socksCreate.getQuantity());
            log.info("{} Изменен в базе данных", socks);
        }
    }


    @Override
    @Transactional
    public void reduceQuantitySocks(SocksCreate socksCreate) {
        SocksId socksId = mapper.toSocksId(socksCreate);
        Optional<Socks> socksOrEmpty = socksRepository.findById(socksId);
        reduce(socksCreate, socksOrEmpty);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getQuantitySocks(SocksCount socksCount) {
        return customRepository.getCountPartSocks(
                socksCount.getColor(),
                symbols.get(socksCount.getOperation()),
                socksCount.getCottonPart()
        );
    }

    private void reduce(SocksCreate socksCreate, Optional<Socks> socksOrEmpty) {
        String message;
        if (socksOrEmpty.isEmpty()) {
            message = "Данный тип носков в базе не сущестует";
            log.debug(message);
            throw new ResourceNotFoundException(message);
        }
        Socks socks = socksOrEmpty.get();
        if (socks.getQuantity() < socksCreate.getQuantity()) {
            message = "Количество меньше запрашиваемого и = " + socks.getQuantity();
            log.debug(message);
            throw new ResourceNotFoundException(message);
        }
        socks.setQuantity(socks.getQuantity() - socksCreate.getQuantity());
        log.info("{} Изменен в базе данных", socks);
    }
}
