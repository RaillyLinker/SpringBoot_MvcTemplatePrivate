package com.raillylinker.springboot_mvc_template_private.use_components

// [Naver SMS 발송 유틸 객체]
interface NaverSmsSenderComponent {
    // (SMS 보내기)
    fun sendSms(inputVo: SendSmsInputVo): Boolean

    // (알림톡 보내기)
    fun sendAlimTalk(inputVo: SendAlimTalkInputVo): SendAlimTalkOutputVo?


    // ---------------------------------------------------------------------------------------------
    // <중첩 클래스 공간>
    data class SendSmsInputVo(
        // 메세지 타입 (SMS, LMS, MMS)
        val messageType: String,
        // 국가 코드 (ex : 82)
        val countryCode: String,
        // 전화번호 (ex : 01000000000)
        var phoneNumber: String,
        // 문자 본문
        var content: String
    )

    data class SendAlimTalkInputVo(
        // 카카오톡 채널명 ((구)플러스친구 아이디)
        val plusFriendId: String,
        // 템플릿 코드
        val templateCode: String,
        // 메시지(최대 100 개)
        val messages: List<MessageVo>
    ) {
        data class MessageVo(
            // 국가 코드 (ex : 82)
            val countryCode: String,
            // 전화번호 (ex : 01000000000)
            val phoneNumber: String,
            // 알림톡 강조표시 내용, 강조 표기 유형의 템플릿에서만 사용 가능
            val title: String?,
            // 문자 본문 (템플릿에 등록한 문장과 동일해야합니다.)
            val content: String,
            // 알림톡 헤더 내용, 아이템 리스트 유형의 템플릿에서만 사용 가능, 16 bytes 미만 까지 입력 가능
            val headerContent: String?,
            // 아이템 하이라이트, 아이템 리스트 유형의 템플릿에서만 사용 가능
            val itemHighlight: ItemHighlightVo?,
            // 아이템 리스트, 아이템리스트 유형의 템플릿에서만 사용 가능
            val item: ItemVo?,
            // 알림톡 메시지 버튼
            val buttons: List<ButtonVo>?,
            // SMS Failover 사용 여부, Failover가 설정된 카카오톡 채널에서만 사용 가능, 기본: 카카오톡 채널의 Failover 설정 여부를 따름
            var useSmsFailover: Boolean?,
            // Failover 설정
            var failoverConfig: FailOverConfigVo?
        ) {
            data class ItemHighlightVo(
                // 아이템 하이라이트 제목, 아이템 리스트 유형의 템플릿에서만 사용 가능
                // 이미지가 없는 경우 : 최대 30자까지 입력 가능 (2줄), 1줄은 15자까지 입력 가능
                // 이미지가 있는 경우 : 최대 21자까지 입력 가능 (2줄), 1줄은 10자까지 입력 가능, 2줄 초과 시 말줄임 처리
                val title: String,
                // 아이템 하이라이트 설명, 아이템 리스트 유형의 템플릿에서만 사용 가능
                // 이미지가 없는 경우 : 최대 19자까지 입력 가능 (1줄)
                // 이미지가 있는 경우 : 최대 13자까지 입력 가능 (1줄), 1줄 초과 시 말줄임 처리
                val description: String
            )

            data class ItemVo(
                // 아이템 리스트, 아이템리스트 유형의 템플릿에서만 사용 가능, 최소 2개 이상, 최대 10개
                val list: List<ListItemVo>,
                // 아이템 요약 정보, 아이템리스트 유형의 템플릿에서만 사용 가능
                val summary: SummaryVo?
            ) {
                data class ListItemVo(
                    // 아이템 리스트 제목, 아이템리스트 유형의 템플릿에서만 사용 가능, 최대 6자까지 입력 가능
                    val title: String,
                    // 아이템 리스트 설명, 아이템리스트 유형의 템플릿에서만 사용 가능, 최대 23자까지 입력 가능
                    val description: String
                )

                data class SummaryVo(
                    // 아이템 요약 제목, 아이템리스트 유형의 템플릿에서만 사용 가능, 최대 6자까지 입력 가능
                    val title: String,
                    // 아이템 요약 설명, 아이템리스트 유형의 템플릿에서만 사용 가능,
                    // 허용되는 문자: 통화기호(유니코드 통화기호, 元, 円, 원), 통화코드 (ISO 4217), 숫자, 콤마, 소수점, 공백
                    // 소수점 2자리까지 허용, 최대 23자까지 입력 가능
                    val description: String
                )
            }

            data class ButtonVo(
                /*
                    type        name        필수 항목
                    DS          배송 조회
                    WL          웹 링크      linkMobile, linkPc (http:// 또는 https://로 시작하는 URL)
                    AL          앱 링크      schemeIos, schemeAndroid
                    BK          봇 키워드
                    MD          메시지 전달
                    AC          채널 추가    버튼 명은 채널 추가 로 고정
                 */
                // 버튼 Type
                val type: String,
                // 버튼명
                val name: String,
                val linkMobile: String?,
                val linkPc: String?,
                val schemeIos: String?,
                val schemeAndroid: String?
            )

            data class FailOverConfigVo(
                // Failover SMS 메시지 Type, SMS 또는 LMS, 기본: content 길이에 따라 자동 적용(90 bytes 이하 SMS, 초과 LMS)
                val type: String?,
                // Failover SMS 발신번호, 기본: Failover 설정 시 선택한 발신번호, 승인되지 않은 발신번호 사용시 Failover 동작 안함
                val from: String?,
                // Failover SMS 제목, LMS type으로 동작할 때 사용, 기본: 카카오톡 채널명
                val subject: String?,
                // Failover SMS 내용, 기본: 알림톡 메시지 내용 (버튼 제외)
                val content: String?
            )
        }
    }

    data class SendAlimTalkOutputVo(
        // 메시지 전송 결과
        val messageResults: List<MessageResultVo>
    ) {
        data class MessageResultVo(
            // 수신자 국가번호, default: 82
            val countryCode: String?,
            // 수신자 번호
            val to: String,
            // 발송요청 상태 코드, 성공: A000, 실패: 그 외 코드(Desc 항목에 실패 사유가 명시)
            val requestStatusCode: String,
            // 발송 요청 상태 내용
            val requestStatusDesc: String
        )
    }
}