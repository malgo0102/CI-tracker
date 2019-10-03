INSERT into users(username, password, email, phone_no, full_name) VALUES
('m','$2a$10$qmdjsaVFeSDpx9alf6WNzu02HaCOMwSGp3V0xEDug7uAOSIUVBt7S','m@m.m',12345678,'Full Name'),
('n','$2a$10$thuo7yIzBjGiac9JL09DoeDbkW4tn2Bd5KgWAO48V8tInWAiKbd8i','n@n.n',23456789,'No Name');

INSERT INTO items (ci_id, name, registration_date, calibration_date, description, notes,owner,entry_creator_id) VALUES
('h1wq', 'hammer',CURRENT_TIMESTAMP,null,'made of wood and metal','use with nails','m',(SELECT id FROM users WHERE username='m')),
('w1yh', 'wrench',CURRENT_TIMESTAMP,null,'made of metal','use in plumbing','m',(SELECT id FROM users WHERE username='m')),
('s1ui', 'chainsaw',CURRENT_TIMESTAMP,'2019-08-15' ,'complicated tool','use for cutting','m',(SELECT id FROM users WHERE username='m')),
('f1eo', 'flashlight',CURRENT_TIMESTAMP,'2019-08-20','it\'s very bright','Steven, put it back to it\'s place!','n',(SELECT id FROM users WHERE username='n')),
('l1po', 'ladder',CURRENT_TIMESTAMP,null ,'small ladder','12 steps','n',(SELECT id FROM users WHERE username='n')),
('s2es', 'spirit level',CURRENT_TIMESTAMP,null ,null,null,'n',(SELECT id FROM users WHERE username='n')),
('c1rt', 'cordless drill',CURRENT_TIMESTAMP,'2020-08-15','for drilling','remember to charge!','n',(SELECT id FROM users WHERE username='n'));

