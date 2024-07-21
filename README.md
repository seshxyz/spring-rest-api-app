<h2>Микросервис - Хранилище файлов.</h2>

<h3>Решение:</h3>
<p class="text-justify">Данную задачу я решил следующим образом:
Создал класс, описывающий сущность (id, название файла, файл, описание, время создания (т.е. отправки)), которая будет соотноситься с таблицей в базе данных Postgresql, также, естественно, добавлены аннотации для взаимодействия с той самой таблицей.<br>
Дополнительно создал DTO для имеющейся сущности. Используя возможности Spring boot, создал класс "контроллер" (тот сервис, что обслуживает запросы по имеющимся методам/эндпоинтам), аннотированный как Restcontroller, так как пожелал использовать подход в виде REST API.<br>
В контроллере реализовал три метода: загрузку файла с атрибутами (некоторые из которых идут в параметрах запроса (описание, сам файл в обычном виде, некоторые берутся из контекста файла (сам файл, кодированный в base64, его название), при этом возвращается тот самый идентификатор, который был присвоен файл в базе данных;<br>
получение файла (на вход требуется его id) - возвращаются атрибуты и сам файл в кодированном виде base64 (таким и хранится после помещения в БД);<br>
получение списка всех файлов (с использованием пагинации и сортировки, реализовал с помощью PaginationAndSortingRepository интерфейса), есть возможность задать страницу, количество элементов на ней, а также сортировать по дате создания (т.е. отправки в сервис) по убыванию/возрастанию.<br>
Микросервис и база данных вместе были обёрнуты в единый docker контейнер.<br>
Полагаю, что с задачей справился, хотя по окончании работ понимаю, что местами можно было сделать лучше и текущий вид, конечно, может быть далёк от совершенства.
<h3>Инструкция:</h3>
Для поднятия приложения нужен Docker. Необходимо подготовить образы программы, открыть Docker, открыть командную строку, перейти в директорию месторасположения образа микросервиса путём команды cd, далее запустить команду docker load -i {сюда вместо скобок и текста вставить название архива образа}, применить команду.<br>
Далее в докере во вкладке Образы нажимам на кнопку пуска возле нашего образа, после этого создастся и запустится контейнер, когда приложение поднимется - можно следовать дальше.<br>
Для осуществления запросов необходимо использовать ПО, которое умеет отправлять их с наборами параметров, как было предложено и в целом хотел использовать - Postman.<br>
1. Открываем Postman.<br>
2. Открываем новую вкладку.<br>
3. Выбираем метод HTTP запроса (слева от строки ввода адреса), для отправки файла в сервис - POST, для получения одного файла/списка - GET.<br>
4. Вводим соответственно, один из имеющихся вариантов:<br>
- localhost:8081/upload - загрузка/отправка файла.<br>
- localhost:8081/view/{id} - получение файла из базы данных, где {id} - число, он же уникальный идентификатор файла в таблице базы.<br>
- localhost:8081/view/all?page={страница}&size={размер<br>
страницы}&sortRule={сортировка} - получение всех файлов с условиями в виде номера страницы {страница}, количества отображаемых элементов на странице {размер страницы, число}, а также применение сортировки (убывание/возрастание, число) по полю "дата создания" (createdat).<br>
Для последнего варианта требуется использование вкладки Params (параметры запроса), так как все 3 параметра являются обязательными, при этом страница не может быть меньше нуля (или не может быть null), размер страницы не может быть меньше 1 (или не может быть null), сортировка обязательна и включает в себя всего два варинта (текст) Asc (возрастание)/ Desc (убывание).<br>
<h3>Примеры запросов:</h3>
localhost:8081/upload - отправляет файл в сервис, возвращает идентификатор, который был ему присвоем в базе данных.<br>
localhost:8081/view/1 - возращает данные по файлу с идентификатором 1.<br>
localhost:8081/view/all?page=0&size=1&sortRule=Asc - возвращает данные по элементам, сортированны по возрастанию времени создания (отправки), в рамках первой страницы и с её размером в один элемент.<br>
localhost:8081/view/all?page=1&size=2&sortRule=Desc - возвращает данные по элементам, сортированны по убыванию времени создания (отправка), в рамках второй страницы и с её размером в два элемента.<br>
localhost:8081/view/all?page=2&size=10&sortRule=Asc - думаю, объяснять более не требуется.<br>
Данные можно вписать как в адресную строку, так и через блок Params, где нужно ввести 3 пары key-value (ключ-значение), page, size, sortRule (колонка ключ) и цифра/число, цифра/число, фраза Asc или Desc, после заполнения они также пропишутся в строку, она будет выглядеть ровно также, но только порядок может быть другой, в зависимости от того, в каком порядке это набирать.<br>
Условно, если выполнить запрос с количеством элементов в базе 11, в параметры указать: страница 1 (системно страница номер 2 == цифре 1), количество элементов 10, то вернётся один элемент, так как, просчитано 10 страниц, на каждой из которых фактически должно было отображаться по 1-ому элементу, на 10-ой странице 11-ый элемент, последний из списка.<br>
</p>
