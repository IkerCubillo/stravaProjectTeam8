package es.deusto.ingenieria.sd.auctions.server.data.dto;

import es.deusto.ingenieria.sd.auctions.server.data.domain.User1;

//This class is part of the DTO pattern. It also implements Singleton Pattern.
public class User1Assembler {
	private static User1Assembler instance;

	private User1Assembler() { }
	
	public static User1Assembler getInstance() {
		if (instance == null) {
			instance = new User1Assembler();
		}

		return instance;
	}

	public UserDTO userToDTO(User1 user) {
		UserDTO dto = new UserDTO();
		
		dto.setEmail(user.getEmail());
		dto.setNickname(user.getAccount());
		
		return dto;
	}
}
