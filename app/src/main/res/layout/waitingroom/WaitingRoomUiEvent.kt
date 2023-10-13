

data class WaitingRoomUiEvent(
    val isUserLeaveMeeting: Boolean = false,
    val isMeetingStarted: Boolean = false,
    val meetingId: String? = null,
    val isEnterWaitingRoom: Boolean = false
)