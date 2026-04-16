LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books`
    DISABLE KEYS */;
INSERT INTO `books`
VALUES (1, 'system', '2026-04-14 15:55:15', NULL, NULL, 'Shinkanzen Master N2', '新完全マスターN2', NULL, NULL),
       (2, 'system', '2026-04-14 15:55:15', NULL, NULL, 'Dekiru Nihongo Xanh', '中級できる日本語', NULL, NULL),
       (3, 'system', '2026-04-14 15:55:15', NULL, NULL, 'Pawaa Doriru N2', 'パワードリルN2', NULL, NULL),
       (4, 'system', '2026-04-14 15:55:15', NULL, NULL, 'Chokuzen Taisaku N2', '直前対策N2', NULL, NULL);
/*!40000 ALTER TABLE `books`
    ENABLE KEYS */;
UNLOCK TABLES;

