## Nhiệm vụ của bạn

- Tôi mới sửa lại controller hàm `getLessonById`, đặc biệt là chỗ lấy Kanji Lesson.
- Giờ tôi muốn sửa lại giao diện xem chi tiết 1 bài kanji
  `japan-go-fe\app\[locale]\(protected)\lesson\kanji\[slug]\page.tsx` như sau:

1. Phần header, giữ nguyên như hiện tại (gồm tên bài, các buttons như lưu, chia sẻ...)
2. Sẽ có 1 tab để chuyển giữa các main kanji, có thể dùng mũi tên lên xuống để chuyển, khi tôi ấn số trên bàn phím thì
   nó cũng sẽ nhảy sang trang đó với số tương ứng.

3. Có thanh công cụ để ẩn hiện nội dung, bao gồm:

- Ẩn/hiện nét vẽ
- Ẩn/hiện tiếng Nhật
- Ẩn/hiện phiên âm Hán Việt
- Ẩn/hiện cách đọc
- Ẩn/hiện nghĩa
- Ẩn/hiện chú ý
- Ẩn hiện support vocabulary
- Tôi nghĩ là làm nó thành các nốt tròn tròn như kiểu trên macos để tránh chiếm nhiều không gian, khi hover vào thì hiện
  nên nội dung của nút đó nghĩa là gì bằng tooltips

4. Trong từng trang (gọi là Kanji Page) thì sẽ có:

- 1 kanji chính, bao gồm cả nét vẽ, âm On, âm Kun, phiên âm Hán việt chính của từ đó
- Có danh sách các vocabulary của kanji page đó, mỗi vocabulary phải hiển thị: nét vẽ của japanese,japanese,
  sinoVietnamese, reading, meaning, note
- Với các từ vựng có support vocabulary thì cũng phải hiện thị nó đầy đủ thông tin
- Bạn hãy bố trí sao cho dễ nhìn, dễ học và có thẩm mỹ cao.