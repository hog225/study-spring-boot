package org.yg.practice.security.services;

import org.yg.practice.security.datas.dto.MfaDto;
import org.yg.practice.security.datas.dto.MfaInitDto;
import org.yg.practice.security.datas.dto.MfaProveDto;
import org.yg.practice.security.datas.entities.User;
// import org.yg.practice.security.repositories.UserRepository;

public interface MfaService {
    
    
    public MfaDto getMfa(String username);

    public MfaProveDto getMfaSecretKey(String username);

    public MfaInitDto setMfa(MfaInitDto mfaInitDto);
    public MfaDto setMfa(MfaDto mfaDto);

    public void deleteMfa(String username);
}
