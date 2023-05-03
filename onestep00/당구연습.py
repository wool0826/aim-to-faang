def solution(m, n, startX, startY, balls):
    answer = []
    for ball in balls:
        answer.append(min([
            ((startX+ball[0])**2 + (startY-ball[1])**2) if startY != ball[1] or startX < ball[0] else 1000*1000,
            ((2*m-startX-ball[0])**2 + (startY-ball[1])**2) if startY != ball[1] or startX > ball[0] else 1000*1000,
            ((startX-ball[0])**2 + (startY+ball[1])**2) if startX != ball[0] or startY < ball[1] else 1000*1000,
            ((startX-ball[0])**2 + (2*n-startY-ball[1])**2) if startX != ball[0] or startY > ball[1] else 1000*1000,
        ]))
        
    return answer
