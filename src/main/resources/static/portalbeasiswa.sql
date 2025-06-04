CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    nim VARCHAR(20) UNIQUE NOT NULL,
    year_of_entry INT NOT NULL,
    study_program VARCHAR(100),
    faculty VARCHAR(100),
    email VARCHAR(100) UNIQUE NOT NULL,
    whatsapp VARCHAR(20),
    password VARCHAR(255) NOT NULL,
    address TEXT,
    
    semester1 DECIMAL(3,2) NOT NULL,
    semester2 DECIMAL(3,2),
    semester3 DECIMAL(3,2),
    semester4 DECIMAL(3,2),
    semester5 DECIMAL(3,2),
    semester6 DECIMAL(3,2),
    semester7 DECIMAL(3,2),
    semester8 DECIMAL(3,2),

    gpa DECIMAL(3,2), -- IPK kumulatif

    transcript_path VARCHAR(255),     -- path/filename dari file PDF
    profile_user VARCHAR(255),    -- path/filename dari foto profil

    role ENUM('USER', 'ADMIN') DEFAULT 'USER',

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

--notif ini bisa diinput admin atau cp jadi bisa ada usernya, notif juga dikategorikan
CREATE TABLE notifications (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    title VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,
    type ENUM('Scholarship Updates', 'Articles & Blogs', 'Q&A', 'System Updates', 'Other') DEFAULT 'Other',
    link VARCHAR(255), --buat masuk page notifnya, jadi setiap ada notif seharusnya kebentuk pagenya
    status ENUM('unread', 'read') DEFAULT 'unread',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE scholarships (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    tumbnail VARCHAR(255), 
    title VARCHAR(255),
    company VARCHAR(255),
    status ENUM('open', 'closing soon', 'close'),
    deadline DATE,
    description TEXT,
    amount DECIMAL(15,2) NOT NULL,
    link VARCHAR(255), 
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE applications (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    scholarship_id BIGINT,
    status ENUM('submitted', 'pending_review', 'approved', 'rejected'),
    submitted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (scholarship_id) REFERENCES scholarships(id)
);

CREATE TABLE letter_submissions (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    application_id BIGINT,
    status ENUM('draft', 'submitted') NOT NULL,
    reason TEXT, -- alasan pengajuan
    file_path VARCHAR(255), -- file PDF
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (application_id) REFERENCES applications(id) ON DELETE CASCADE

);

CREATE TABLE letter_statuses (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    letter_submission_id BIGINT,
    status ENUM('in_progress', 'complete') NOT NULL,
    reviewed_pdf_path VARCHAR(255),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (letter_submission_id) REFERENCES letter_submissions(id)
);

CREATE TABLE application_forms (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    application_id BIGINT,
    motivation TEXT, -- alasan mengikuti beasiswa
    guardian_lecturer_name VARCHAR(100),
    guardian_lecturer_nidn VARCHAR(50),
    other_info TEXT,
    FOREIGN KEY (application_id) REFERENCES applications(id) ON DELETE CASCADE

);

CREATE TABLE selection_statuses (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    application_id BIGINT,
    score DECIMAL(5,2),
    status ENUM('accepted', 'rejected'),
    rejection_note TEXT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (application_id) REFERENCES applications(id) ON DELETE CASCADE

);

CREATE TABLE verifications (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    application_id BIGINT,
    bank_name VARCHAR(100),
    bank_account_number VARCHAR(50),
    whatsapp_group_link VARCHAR(255),
    contact_person VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (application_id) REFERENCES applications(id) ON DELETE CASCADE

);

CREATE TABLE agreement_forms (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    application_id BIGINT,
    agreement_pdf_path VARCHAR(255),
    submitted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (application_id) REFERENCES applications(id) ON DELETE CASCADE

);

CREATE TABLE funds_statuses (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    application_id BIGINT,
    amount DECIMAL(15,2),
    status ENUM('sent', 'received', 'processing') NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (application_id) REFERENCES applications(id) ON DELETE CASCADE

);

-- Jumlah beasiswa yang masih terbuka (baik 'open' maupun 'closing soon')
SELECT COUNT(*) FROM scholarships WHERE status IN ('open', 'closing soon');
-- Jumlah pengajuan beasiswa oleh user tertentu
SELECT COUNT(*) FROM applications WHERE user_id = ?;
-- Jumlah pengajuan beasiswa user yang masih menunggu review
SELECT COUNT(*) FROM applications WHERE user_id = ? AND status = 'pending_review';
SELECT COUNT(*) FROM applications WHERE user_id = ? AND status = 'rejected';
SELECT COUNT(*) FROM applications WHERE user_id = ? AND status = 'approved';
-- Jumlah beasiswa yang deadline-nya 7 hari ke depan dan status masih bisa dilamar
SELECT COUNT(*) FROM scholarships WHERE status IN ('open', 'closing soon')  AND deadline BETWEEN CURRENT_DATE() AND DATE_ADD(CURRENT_DATE(), INTERVAL 7 DAY);


CREATE TABLE articles (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255),
    description VARCHAR(300),
    tumbnail VARCHAR(255),      -- path/filename dari foto profil
    link_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE qna (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    question_text TEXT NOT NULL,
    answer_text TEXT, -- bisa NULL jika belum dijawab
    category ENUM('Registration', 'Requirements', 'Documents', 'Selection', 'Funding', 'Other') NOT NULL DEFAULT 'Other',
    submitted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    answered_at TIMESTAMP NULL,
    
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE contact_person (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    role ENUM('WhatsApp', 'Instagram') NOT NULL,
    bio TEXT,
    foto_profile VARCHAR(255),    -- path/filename dari foto profil
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE social_media_contact (
    id INT AUTO_INCREMENT PRIMARY KEY,
    platform ENUM('WhatsApp', 'Instagram') NOT NULL,
    contact_info VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE guide (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    file_path VARCHAR(500) NOT NULL, -- Simpan path atau URL file guide
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);



