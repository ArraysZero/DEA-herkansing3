//package nl.dani.han.mappers;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.mock;
//
//import org.junit.jupiter.api.Test;
//
////import nl.dani.han.exceptions.PlaylistException;
////import nl.dani.han.exceptions.mappers.PlaylistExceptionMapper;
//
//public class PlaylistExceptionMapperTest {
//
//	PlaylistException mockException = mock(PlaylistException.class);
//
//	PlaylistExceptionMapper sut = new PlaylistExceptionMapper();
//
//	@Test
//	public void toResponseTest() {
//		var actual = sut.toResponse(mockException);
//
//		assertEquals(403, actual.getStatus());
//	}
//}